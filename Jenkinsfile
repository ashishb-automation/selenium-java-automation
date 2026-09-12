pipeline {
    agent any

    tools {
        maven 'Maven'
        jdk 'JDK17'
    }

    parameters {
        choice(name: 'BROWSER', choices: ['chrome', 'firefox'], description: 'Browser for the regression suite')
    }

    environment {
        CI = 'true'
        MAVEN_OPTS = '-Xmx1024m'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Validate Environment') {
            steps {
                sh 'java -version'
                sh 'mvn -version'
            }
        }

        stage('Run Cucumber Regression') {
            steps {
                withEnv(["BROWSER=${params.BROWSER}"]) {
                    sh 'mvn --batch-mode --no-transfer-progress clean test'
                }
            }
        }
    }

    post {
        always {
            junit allowEmptyResults: true, testResults: 'target/surefire-reports/*.xml'
            archiveArtifacts allowEmptyArchive: true,
                    artifacts: 'target/cucumber-report.html,target/cucumber-html-reports/**',
                    fingerprint: true
        }
    }
}
