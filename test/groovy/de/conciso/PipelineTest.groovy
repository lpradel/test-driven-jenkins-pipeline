package de.conciso

import com.lesfurets.jenkins.unit.declarative.DeclarativePipelineTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

import static org.junit.jupiter.api.Assertions.assertThrows

class PipelineTest extends DeclarativePipelineTest {

    @BeforeEach
    void setUp() throws Exception {
        super.setUp()
    }

    @Nested
    class GivenMavenFails {

        @BeforeEach
        void setUp() throws Exception {
            helper.addShMock('mvn clean install', '', 1)
        }

        @Nested
        class WhenRunPipeline {

            @Test
            void thenPipelineShouldFail() throws Exception {
                assertThrows(java.lang.reflect.UndeclaredThrowableException) {
                    runScript('Jenkinsfile')
                }
            }
        }
    }

    @Nested
    class GivenMavenSucceeds {

        @BeforeEach
        void setUp() throws Exception {
            helper.addShMock('mvn clean install', '', 0)
        }

        @Nested
        class WhenRunPipeline {

            @BeforeEach
            void setUp() throws Exception {
                runScript("Jenkinsfile")
            }

            @Test
            void thenPipelineShouldExecuteWithoutErrors() throws Exception {
                assertJobStatusSuccess()
                printCallStack()
            }
        }

        @Nested
        class AndReadFileContainsFailed {

            @BeforeEach
            void setUp() throws Exception {
                helper.addReadFileMock('output', 'FAILED!!!') // given
            }

            @Nested
            class WhenRunPipeline {

                @BeforeEach
                void setUp() throws Exception {
                    runScript("Jenkinsfile")
                }

                @Test
                void thenPipelineShouldExecuteWithoutErrors() throws Exception {
                    assertJobStatusFailure()
                }
            }
        }
    }
}
