**Descartes** is a tool that implements extreme mutation testing and discovers *pseudo-tested methods*.
This file contains a set of activities to learn how to use the tool and the concepts behind it.
More information about such methods and Descartes can be found [here](https://github.com/STAMP-project/pitest-descartes).

##Prerequisites

1. Java 8+
2. Maven 3+
3. The following packages and their dependencies:
    * Descartes 1.2.4
    * PitMP 1.3.4
    * PITest Maven 1.4.2 
If you have an Internet connection Maven will take care of downloading the packages and all their dependencies.
4. A local Jenkins installation. (**Optional**) 

## Exercises

1. Clone [the repository from Github](https://github.com/STAMP-project/ptm-examples) and set the newly created directory as your working directory:

    ```
    git clone 
    ```

2. The easiest way to use **Descartes** is through [PitMP](https://github.com/STAMP-project/pitmp-maven-plugin). To do that execute the following command:

    ```
    mvn clean test eu.stamp-project:pitmp-maven-plugin:descartes
    ```

    The command above will instruct Maven to first clean the project, then run the tests and finally run PitMP using Descartes. PitMP is a tool that allows to use [PIT](http://pitest.org) to analyze Maven multi-module projects. While `clear` and `test` goals are optional, it is important to have build the project and be sure that all tests are green before using PIT or PitMP, otherwise the goal will fail.
    If the execution was successful you should see a `pit-reports` folder insider the `target` folder and there a folder named after the current date and time. Inside this folder there should be an `HTML` report. That is the default report created by PIT. It contains human-readable information about the mutants that were created and their status, that is, if they were covered and detected or not. Explore the report and try to determine which methods could be pseudo-tested.

3. Build a Descartes report to know the pseudo-tested methods in the project. For that execute:

    ```
    mvn clean test eu.stamp-project:pitmp-maven-plugin:descartes -DoutputFormats=ISSUES
    ```
    
    This will create a new report in a similar path than the one from the previous activity. This report highlights the methods found to have testing issues and information about the mutations performed and the executed test cases. Explore the report. There is also a `JSON` version of this report, more suitable for automated tasks. For that use `METHODS` instead of `ISSUES`. Other PIT report formats can be used as well. You may want to try `XML`, `CSV` or `JSON`. A comma separated list of these reports can be specified as well to obtain more than one format at the same time.

4. So far, we have mostly used the default configurations of Descartes and PitMP. The configuration can be altered from the command line in the same way it was done in the previous task to obtain different reports. Descartes and PitMP rely on the options provided by PIT. These options allow to change, not only the report format, but the set of mutation operators to use, classes and methods to avoid and skip and additional features. More information can be found [here](https://github.com/STAMP-project/pitest-descartes).
    As in every Maven project, the configuration can be specified in the `pom.xml` file. You can add the following snippet to this file in the `plugins` section:

    ```xml
    <plugin>
      <groupId>eu.stamp-project</groupId>
      <artifactId>pitmp-maven-plugin</artifactId>
      <version>1.3.4</version>
      <configuration>
        <outputFormats>
          <value>ISSUES</value>
        </outputFormats>
      </configuration>
    </plugin>
    ```

    Now, PitMP can be invoked in the following way:

    ```
    mvn pitmp:descartes
    ```

    The set of mutation operators used by Descartes can be configured. You may specify any Java literal to target methods returning primitive types or `String`, `null` to target reference objects, `empty` to use empty arrays and `void` for `void` methods. The default configuration can be seen [here](https://github.com/STAMP-project/pitest-descartes).

    Add the folling to the `configuration` section:

    ```xml
    <mutators>
      <mutator>(byte)3</mutator>
      <mutator>4</mutator>
      <mutator>true</mutator>
      <mutator>"A string"</mutator>
    </mutators>
    ```

5. Descartes can be used directly with PIT. Add the following to `pom.xml`:

    ```xml
    <plugin>
      <groupId>org.pitest</groupId>
      <artifactId>pitest-maven</artifactId>
      <version>1.4.2</version>
      <dependencies>
        <dependency> <!--Descartes as a dependency-->
          <groupId>eu.stamp-project</groupId>
          <artifactId>descartes</artifactId>
          <version>1.2.4</version>
        </dependency>
      </dependencies>
      <configuration>
        <mutationEngine>descartes</mutationEngine> <!--Active mutation engine-->
        <outputFormats>
          <value>ISSUES</value>
        </outputFormats>
      </configuration>
    </plugin>
    ```

    Notice how Descartes was declared as a dependency for PIT and how it was set as the active mutation engine. 
    
    Now run:

    ```
    mvn org.pitest:pitest-maven:mutationCoverage
    ```

    This can be useful to access PIT features not supported yet by PitMP.

6. PIT allows to exclude or include classes and methods in the analysis using wildcards to match their names. Descartes also provides a set a filters based on the structure of the methods. For example the tool can skip methods whose code returns only the value of a field, like a simple getter. All the targeted code patterns can be consulted [here](). They are generally not targeted by  It is possible to re-include these methods. For this specify the following in `configuration`:

    ```xml
    <features>
        <feature>+STOP_METHODS(except[setter])</feature>
    </features>
    ```

    To avoid using this feature add the following:

    ```xml
    <features>
        <feature>-STOP_METHODS()</feature>
    </features>
    ```
    Include setter methods in the analysis, re-run Descartes on the given project and compare the results. Explore different options.

7. (**Requires Jenkins**) There is a Jenkins plugin available to help monitor the mutation score and pseudo-tested methods set provided by Descartes across several builds. It is under development and can be checked [here](https://github.com/STAMP-project/jenkins-stamp-report-plugin).

    To us it, first configure a free-style job, targeting the given project and add a Maven step with the appropiate goal. For example:

    ```
    clean test pitmp:descartes
    ```

    The above requires to configure the `pom.xml` file.

    Add the STAMP post-build action. Specify a custom path in the advanced options:

    ```
    target/pit-reports/*/methods.json
    ```

    Launch the job and check the results.

8. Solve the pseudo-tested methods in the given project. Use the Jenkins setup or just the tools directly.

9. Try Descartes in a different project. Find a pseudo-tested method. Try to solve it. Share your results.


