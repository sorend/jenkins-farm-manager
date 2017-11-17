package dk.bankdata.devops

import de.gesellix.docker.client.DockerClient
import groovy.mock.interceptor.MockFor
import org.junit.After
import org.junit.Before
import org.junit.Test

import static junit.framework.Assert.assertTrue

class FarmHelperTest {

    FarmHelper suc
    File tempDir


    @Before
    void setup() throws Exception {
        tempDir = File.createTempDir()
    }

    @After
    void teardown() throws Exception {
        tempDir.deleteDir()
    }


    @Test
    void testCreateComposeFromTemplate() {

        suc = new FarmHelper()

        def jenkins = [version: '1.0.0', jnlp_port: 50001, home: '/home/tnt/whatever', executors: 0]
        def slaves = [1, 2]
        def jenkins_swarm = [version: '1.0.0', executors: 2]

        def file = suc.buildStackFile("jenkins-with-swarm-slaves", tempDir, name: "devoopsie", jenkins: jenkins, slaves: slaves,
        jenkins_swarm: jenkins_swarm)

        assertTrue(file.exists())

        println file.text
    }

}
