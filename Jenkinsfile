pipeline {
    agent any 

    triggers {
        pollSCM('* * * * *')
    }
    // Got permission denied while trying to connect to the Docker daemon socket at unix.
    // sudo usermod -a -G docker jenkins
    // restart jenkins server ->  sudo service jenkins restart

    environment
    {
        DBUN="${DBUN}"
        DBPW="${DBPW}"
    }

    stages {
            
        stage('Maven Compile') {
            steps {
                echo '----------------- Compiling project ----------'
                sh 'mvn clean compile'
            }
        }
        
        stage('Maven Build') {
             steps {
                echo '----------------- Building project ----------'
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Docker Build') {
            steps {
                echo '----------------- Building docker image ----------'
                sh '''
                    docker image build -t order-service .
                '''
            }
        }

        stage('Docker Deploy') {
            steps {
                echo '----------------- Deploying docker image ----------'
                sh '''
                 (if  [ $(docker ps -a | grep order-service | cut -d " " -f1) ]; then \
                        echo $(docker rm -f order-service); \
                        echo "---------------- successfully removed order-service ----------------"
                     else \
                    echo OK; \
                 fi;);
            docker container run \
            --env DBUN=$DBUN \
            --env DBPW=$DBPW \
            --restart always \
            --name order-service \
            -p 8083:8083 \
            -d order-service && \
            docker network connect foodie-inc-network order-service
            '''
            }
        }
    }
}