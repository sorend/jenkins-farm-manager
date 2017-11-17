package dk.bankdata.devops

import com.github.jknack.handlebars.Handlebars
import com.github.jknack.handlebars.io.ClassPathTemplateLoader
import com.github.jknack.handlebars.io.FileTemplateLoader
import de.gesellix.docker.client.DockerClient
import de.gesellix.docker.client.DockerClientImpl
import de.gesellix.docker.client.stack.DeployConfigReader
import de.gesellix.docker.client.stack.DeployStackOptions


class FarmHelper {

    DockerClient docker = new DockerClientImpl("unix:///var/run/docker.sock")
    Handlebars handlebars = new Handlebars(new ClassPathTemplateLoader("/", ".mustache"))

    File buildStackFile(Map values, String template, File outputDir) {
        assert values.'name' : "'name' parameter not given"
        // make compose from template
        def hbTemplate = handlebars.compile(template)
        def sw = new StringWriter()
        hbTemplate.apply(values, sw)
        // save and return
        def outputFile = new File(outputDir, "${values.'name'}.yml")
        outputFile.text = sw.toString()
        outputFile
    }

    void deployStack(String name, File composeFile) {

        def config = new DeployConfigReader(docker).loadCompose(name, composeFile.newInputStream(), composeFile.parent, [:])
        def options = new DeployStackOptions()
        docker.stackDeploy(name, config, options)
    }

    def listStacks() {
        docker.lsStacks()
    }

    void resetStack(String name, File composeFile) {
        // t'kurva
        docker.stackRm(name)
    }

}
