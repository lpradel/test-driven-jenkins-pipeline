package de.conciso

import com.lesfurets.jenkins.unit.declarative.DeclarativePipelineTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import static org.junit.jupiter.api.Assertions.assertThrows

class PipelineTest extends DeclarativePipelineTest {

    @BeforeEach
    void setUp() throws Exception {
        super.setUp()
        helper.addShMock('mvn clean install', '', 0)
    }

    @Test
    void should_execute_without_errors() throws Exception {
        runScript("Jenkinsfile") // when
        assertJobStatusSuccess() // then
        printCallStack()
    }

    @Test
    void should_read_file_and_fail() throws Exception {
        helper.addReadFileMock('output', 'FAILED!!!') // given
        runScript("Jenkinsfile") // when
        assertJobStatusFailure() // then
    }

    @Test
    void should_fail_if_mvn_fail() throws Exception {
        helper.addShMock('mvn clean install', '', 1) // given
        assertThrows(java.lang.reflect.UndeclaredThrowableException) { // then
            runScript('Jenkinsfile') // when
        }
    }
}
