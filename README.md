Test-driven CI/CD Jenkins pipelines
==================
![maven build](https://github.com/lpradel/test-driven-jenkins-pipeline/workflows/maven-build/badge.svg)

This is a Maven-based project demonstrating how to unit-test declarative Jenkins pipelines with the
help of the [Jenkins Pipeline Unit](https://github.com/jenkinsci/JenkinsPipelineUnit) framework.

## Installation

The build and dependency management of this project is based on [Apache Maven](https://maven.apache.org/).

### Maven

Run the following command in your preferred shell:

```shell
mvn clean verify
```

### Dependencies

This project has the following dependencies:
- Java 11
- JUnit 5
- Groovy 3
- [Jenkins Pipeline Unit](https://github.com/jenkinsci/JenkinsPipelineUnit) 1.9

## Usage

This project demonstrates how test-driven development of declarative Jenkins pipelines can be done.
For this purpose it relies heavily on the
[Jenkins Pipeline Unit](https://github.com/jenkinsci/JenkinsPipelineUnit) framework which allows unit-testing
of Jenkins pipelines.

As is tradition in test-driven development, begin by writing, changing or expanding the Groovy-based unit-tests
in `test/groovy/` and ensure that the names your unit test classes end with `*Test.groovy` as is
expected by convention. Once your tests fail, start implementing the necessary changes in `Jenkinsfile`
which is by convention expected to be in `src/main/jenkins`.

Once your tests are successful, begin to clean up the mess you made by refactoring your code.

Finally, start over as you expand your Jenkins pipeline :construction_worker:.

## Contributing

1. Fork it!
2. Create your feature branch: `git checkout -b my-new-feature`
3. Commit your changes: `git commit -am 'Add some feature'`
4. Push to the branch: `git push origin my-new-feature`
5. Submit a pull request :grinning:

## License

    Copyright 2020 Lukas Pradel
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
      http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
