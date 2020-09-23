// PipelineTest.groovy
package de.conciso

import com.lesfurets.jenkins.unit.declarative.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException

class PipelineTest extends DeclarativePipelineTest {

  @Rule
  public ExpectedException thrown = new ExpectedException()

  @Override
  @Before
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
    thrown.expect(java.lang.reflect.UndeclaredThrowableException) //then
    runScript("Jenkinsfile") // when
  }
}
