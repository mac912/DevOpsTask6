job('job2.'){
description('we made this job for deployment')
triggers {
        upstream('seed_job', 'SUCCESS')
    }

steps {
shell('''if ! kubectl get pvc | grep php-pvc
         then
          kubectl create -f /root/.jenkins/workspace/seed_job/php_pvc.yml
         fi
         if ls /root/.jenkins/workspace/seed_job/ | grep .php
         then
          if kubectl get deploy | grep php
          then 
           PODS=$(kubectl get pods -l app=php -o jsonpath="{.items[*].metadata.name}")
           for i in $PODS
            do 
             kubectl cp /root/.jenkins/workspace/seed_job/*.php $i:/var/www/html/
            done
          else
           kubectl  create -f /root/.jenkins/workspace/seed_job/php.yml
           sleep 25
           PODS=$(kubectl get pods -l app=php -o jsonpath="{.items[*].metadata.name}")
           for i in $PODS
            do 
             kubectl cp /root/.jenkins/workspace/seed_job/*.php $i:/var/www/html/
            done
          fi 
         fi  
         if ! kubectl get svc | grep php-deploy-svc
         then
          kubectl create -f /root/.jenkins/workspace/seed_job/php_svc.yml
         fi''') 
}
} 

