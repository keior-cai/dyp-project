pipeline {
  agent none
    parameters {
      string(name: 'version', default: 'v1.00.00', description: '指定发布版本')
      string(name: 'env', defaultValue: 'dev', description: '指定要发布的环境，test/dev')
      string(name: 'moduleName', defaultValue: 'dyp', description: '指定要发布的模块名称，dyp')
      string(name: 'iPort', defaultValue: '8088', description: '指定要绑定的内部端口，8088')
    }
  stages {
    stage('init') {
      agent any
      steps {
        script{
          if (params.env == 'test') {
            DEPLOY_SERVERS = ['47.106.78.139']
            USER = 'dev'
            TYPE = 'test'
            CREDENTIALS = 'abce3891-44dd-409f-a9fc-66c4d6253c7f'
          } else if (params.env == 'dev') {
            DEPLOY_SERVERS = ['47.106.78.139']
            USER = 'root'
            TYPE = 'dev'
            CREDENTIALS = '28bc5121-16fd-44d4-9928-b2551090fe8e'
          }
        }
      }
    }
  }
  stages {
    stage('package'){
      agent {
        docker {
          image 'maven:3.5.3-jdk-8'
          args '-v /var/jenkins_home/docker/data/maven/root/.m2:/root/.m2'
        }
      }
      steps {
        script{
          sh "mvn clean package -DskipTests"
      	}
      }
    }
  }
  stage('deploy'){
  	agent any
  	steps{
  	  script {
  		sshagent(credentials: [CREDENTIALS]){
  		  for(String DEPLOY_SERVER:DEPLOY_SERVERS){
  			sh "ssh -o StrictHostKeyChecking=no ${USER}@${DEPLOY_SERVER} rm -rf /data/pkg/dyp/operation/dyp_${params.version}"
  		  }
  		}
  	  }
  	}
  }
}