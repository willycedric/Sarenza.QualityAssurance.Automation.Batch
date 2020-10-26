pipeline {
    agent any
    tools {
     //   maven 'Maven 3.6.3'
      //  jdk 'jdk8'
    }
    stages {
        stage ('Initialize') {
            steps {

                    echo "PATH = %PATH%"
                    echo "M2_HOME = %M2_HOME%"

            }
        }

        stage ('Test') {
            steps {
                'mvn test -Dbrowser=chrome_remote'
            }
            post {
                success {
                    junit 'target/surefire-reports/**/*.xml'
                }
            }
        }
    }
}