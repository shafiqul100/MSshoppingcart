pipeline {
  tools {
        maven 'Maven3'
    }
    agent any
        environment {
        // //registryName = "microservicerepository"
        // registryName="microservicerepository/shoppingcart:${currentBuild.number}"
        // //- update your credentials ID after creating credentials for connecting to ACR
        // registryCredential = 'ACR'
        // dockerImage = ''
        // registryUrl = 'microservicerepository.azurecr.io'

        registryName="shafiq53/shoppingcart:${currentBuild.number}"
        //- update your credentials ID after creating credentials for connecting to ACR
        registryCredential = 'dockerhub'
        dockerImage = ''
        //registryUrl = 'hub.docker.com'
        DOCKERHUB_CREDENTIALS = credentials('dockerhub')
        registryUrl = 'https://index.docker.io/v1/'
    }
    
    stages {
        stage('checkout') {
            steps {
                //checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[credentialsId: 'git_credentials', url: 'https://github.com/shafiqul100/MS-jenkins-pipeline-spring-boot-product.git']])
                git branch: 'main', credentialsId: 'git_credentials', url: 'https://github.com/shafiqul100/MSshoppingcart.git'
                //checkout([$class: 'GitSCM', branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[credentialsId: 'git_credentials', url: 'https://github.com/shafiqul100/MS-jenkins-pipeline-spring-boot-product.git']]])
                // sh 'cd /var/lib/jenkins/workspace/MS-Product'
                // sh 'cd libs'
                // sh 'cp /var/lib/jenkins/.m2/repository/com/mspoc/msdevkit/0.0.1-SNAPSHOT/msdevkit-0.0.1-SNAPSHOT.jar .'
                // sh 'cd ..'
                //checkout([$class: 'GitSCM', branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'check_out_from_your_repo_after_forking_my_repo']]])
            }
        }
        
        stage ('Build') {
        steps {
            //sh 'mvn install:install-file -Dfile="/var/lib/jenkins/.m2/repository/com/mspoc/msdevkit/0.0.1-SNAPSHOT/msdevkit-0.0.1-SNAPSHOT.jar" -DgroupId=com.mspoc -DartifactId=msdevkit -Dversion=0.0.1-SNAPSHOT -Dpackaging=jar'
            sh 'mvn clean install -DskipTests=true'           
        }
     }
     
    stage ('Build Docker image') {
        steps {
                script {
                    //echo "Jenkins build number is ${BUILD_NUMBER}"
                    //sh 'printenv'
                    dockerImage = docker.build registryName
                }
            }
        }
    stage('Login') {
        steps {
             sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
                }
        }
    // Uploading Docker images into ACR
        stage('Upload Image to ACR') {
         steps{   
             script {
                docker.withRegistry( "${registryUrl}", registryCredential ) {
                dockerImage.push()
                }
            }
          }
        }
        
        stage ('K8S Deploy') {
          steps {
            script {
                withKubeConfig(caCertificate: '', clusterName: '', contextName: '', credentialsId: 'K8S', namespace: '', restrictKubeConfigAccess: false, serverUrl: '') {
                //sh "sed -i 's/latest/${currentBuild.number}/g' jenkins-aks-from-acr-deployment-k8s.yaml"
                sh "kubectl apply -f  jenkins-aks-from-acr-deployment-k8s.yaml"
                sh "kubectl apply -f  jenkins-aks-from-acr-service-k8s.yaml"
                }
            }
        }
     }
    }
}