import static ratpack.groovy.Groovy.ratpack
import static ratpack.jackson.Jackson.json

ratpack {
    handlers {
        get('stacks') {
            render json([
                    [name: 'devoopsie', stack: 'jenkins-with-swarm-slaves-1.0.0'],
                    [name: 'tnt-jenkins', stack: 'moe-setup-1.0.6']
            ])
        }
        get('stacks/:id') {

        }
        post('stacks') {

        }
        put('stacks/:id') {

        }
        delete('stacks/:id') {

        }
        files {
            dir "public"
            indexFiles "index.html"
        }
    }

}