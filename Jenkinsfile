pipeline {
   agent any
   stages {
       stage('package'){
           agent any
           steps {
               script{
                   sh "mvn clean package -DskipTests"
               }
           }
       }
   }
}