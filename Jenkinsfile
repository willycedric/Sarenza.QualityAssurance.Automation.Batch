import org.jenkinsci.plugins.workflow.steps.FlowInterruptedException


pipeline {
    agent {
        node {
            label 'TEST3'
        }
    }
    stages {
        stage ('Initialize') {
            steps {
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                '''
            }
        }
        stage ('Test') {
            steps {
                retry(6) {

               script{
                    try {
                   // timeout(time: 120, unit: 'MINUTES') {

                        // something that can fail
                        sh 'mvn test -Dbrowser=chrome_remote'
                       
                     //   } // timeout ends

                    } finally {
                        // we re-throw as a different error, that would not 
                        // cause retry() to fail (workaround for issue JENKINS-51454)
                        echo 'FlowInterruptedException raised'
                         sh 'sleep 1200'
                        //error 'Timeout!'

                    } // try ends
               }// end script

                } // retry end
            }
            post {
                success {
                    junit 'target/surefire-reports/**/*.xml'
                }
            }
        }
    }
}

