<template>
    <div id="emailVerify" class="message-container">
      <router-link to="/">
          <img src="@/assets/img/002.png" class="css-1k8svhy">
        </router-link>
          <h1>이메일 인증을 완료해주세요</h1>
          <p class="content">
            <strong>BOOTSHELF</strong> 에 가입해주셔서 감사합니다.
          </p>
          <p>이메일 인증 완료 후 BOOTSHELF에 참여해보세요!</p>
        </div>
    </template>
    <script>
    export default {
      name: 'EmailValidationPage',
      mounted() {
       this.$root.hideHeaderAndFooter = true;
       },
     };
    </script>
    <style scoped>
    .css-1k8svhy {
      width: 100%;
      max-width: 300px;
      height: auto;
      -o-object-fit: contain;
      object-fit: contain;
      margin-top: 120px;
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: center;
      margin-left: auto;
      margin-right: auto;
    }
    
    body {
      font-family: Arial, sans-serif;
      text-align: center;
     
      margin: 0;
      padding: 0;
    }
    
    .message-container {
      background-color: #fff;
      border-radius: 17px;
   
      margin: 101px auto;
      max-width: 450px;
    }
    
    p {
      color: #333;
      margin-bottom: 20px;
      height: 100%;
      margin: 0;
      display: flex;
      align-items: center;
      justify-content: center;
    }

    h1 {
      color: #333;
      margin-bottom: 20px;
      height: 100%;
      margin: 0;
      display: flex;
      align-items: center;
      justify-content: center;
    }
    
    
    .content {
      margin-top: 50px;
    }
    
    </style>