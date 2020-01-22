pipeline {
  agent none
    parameters {
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
              DEPLOY_SERVERS = ['10.4.1.221']
              USER = 'dev'
              TYPE = 'test'
              CREDENTIALS = 'abce3891-44dd-409f-a9fc-66c4d6253c7f'
            } else if (params.env == 'zhaoyun') {
              DEPLOY_SERVERS = ['10.4.4.48']
              USER = 'dev'
              TYPE = 'zhaoyun'
              CREDENTIALS = 'cd65958f-0d37-4e76-a2fc-2efeec149537'
            }
          }
        sh "rm -rf ${DOCKER_DIR} && mkdir ${DOCKER_DIR}"
        sh "mkdir ${DOCKER_DIR}/${params.moduleName}"
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
  			sh "ssh -o StrictHostKeyChecking=no ${USER}@${DEPLOY_SERVER} rm -rf /data/pkg/yicall/operation/yicall_${params.yicallVersion}"
  		  }
  		}
  	  }
  	}
  }
}