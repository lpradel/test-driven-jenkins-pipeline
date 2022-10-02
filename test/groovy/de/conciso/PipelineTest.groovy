package de.conciso

import com.lesfurets.jenkins.unit.declarative.DeclarativePipelineTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

import java.lang.reflect.UndeclaredThrowableException

import static org.junit.jupiter.api.Assertions.assertThrows

class PipelineTest extends DeclarativePipelineTest {
    @BeforeEach
    void setUp() {
        super.setUp()
    }

    @Nested
    class GivenMavenFails {
        @BeforeEach
        void setUp() {
            helper.addShMock('mvn clean install', '', 1)
        }

        @Nested
        class WhenRunPipeline {
            @Test
            void thenPipelineShouldFail() {
                assertThrows(UndeclaredThrowableException) {
                    runScript('Jenkinsfile')
                }
            }
        }
    }

    @Nested
    class GivenMavenSucceeds {
        @BeforeEach
        void setUp() {
            helper.addShMock('mvn clean install', '', 0)
        }

        @Nested
        class WhenRunPipeline {

            @BeforeEach
            void setUp() {
                runScript("Jenkinsfile")
            }

            @Test
            void thenPipelineShouldExecuteWithoutErrors() {
                assertJobStatusSuccess()
                printCallStack()
            }
        }

        @Nested
        class AndReadFileContainsFailed {
            @BeforeEach
            void setUp() {
                helper.addReadFileMock('output', 'FAILED!!!')
            }

            @Nested
            class WhenRunPipeline {

                @BeforeEach
                void setUp() {
                    runScript("Jenkinsfile")
                }

                @Test
                void thenPipelineShouldExecuteWithoutErrors() {
                    assertJobStatusFailure()
                }
            }
        }
    }
}
