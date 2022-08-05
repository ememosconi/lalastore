def call(companyName){


    writeFile([file:"$env.WORKSPACE/prepare-wrokspace.sh",text:libraryResource("sh/prepare-wrokspace.sh")])
    withCredentials([usernamePassword(credentialsId: 'murmusecret', passwordVariable: 'password', usernameVariable: 'username')]) {
    sh "chmod +x prepare-wrokspace.sh && ./prepare-wrokspace.sh $companyName"
    }
}