job('job4'){
description(' job4 will send email to developer on failure ')
 
publishers {
        extendedEmail {
            recipientList('manishdwarkas912@gmail.com')
            defaultSubject('failure')
            defaultContent('There is something wrong')
            contentType('text/html')
            triggers {
              always(){
          sendTo{
            recipientList()
                }
            }
        }
    }
}

}
