<template>
    <div class="message-container">
      <img src="@/assets/img/002.png" class="css-1k8svhy">
      <div class="container">
        <h2>어떤 회원으로 가입하시겠습니까?</h2>
        <div class="container-2">
          <div class="button-container">
            <div class="description">
              <p>
                일반회원은 국비지원교육, 부트캠프를
                <br>
                한 번도 수강한 이력이 없는
                회원입니다.&nbsp;&nbsp;&nbsp;&nbsp;
              </p>
              <router-link class="button normal-member" to="/signup">일반 회원가입</router-link>
              <!-- <button class="button normal-member">
                일반 회원가입
              </button> -->
            </div>
          </div>
          <br>
          <div class="button-container">
            <div class="description">
              <p>
                인증회원은 최근 2년 간 국비지원교육, 부트캠프를
                <br>
                한 번이라도 수강한 이력이 있는 회원입니다.
              </p>
              &nbsp;&nbsp;
              <router-link class="button corporate-member" to="/authsignup">인증 회원가입</router-link>
              <!-- <button class="button corporate-member">
                인증 회원가입
              </button> -->
            </div>
          </div>
        </div>
      </div>
    </div>
</template>
<script>

export default {
  name: 'MainSignupPage',
}

</script>
<style scoped>
body {
  font-family: Arial, sans-serif;
  text-align: center;
  margin: 0;
  padding: 0;
}

.container {
  margin: 1px auto;
  background-color: #fff;
  border-radius: 8px;
  padding: 20px;
}

.container-2 {
  margin: 90px auto;
  background-color: #fff;
  border-radius: 8px;
  padding: 20px;
}

h2 {
  color: #333;
}

.button-container {
  display: flex;
  justify-content: center;
  align-items: center;
}

.button {
  display: inline-block;
  padding: 32px 40px;
  font-size: 16px;
  cursor: pointer;
  margin-right: 10px;
  border: none;
  border-radius: 8px;
}

.normal-member {
  background-color: #5bb629;
  color: #fff;
  font-weight: bold;
  box-shadow: rgb(0 0 0 / 15%) 7px 5px 8px;
}

.corporate-member {
  background-color: #541d7a;
  color: #fff;
  font-weight: bold;
  box-shadow: rgb(0 0 0 / 15%) 7px 5px 8px;
}

.description {
  flex-grow: 0.1; 
  text-align: left;
  padding-left: 20px; 
  display: flex;
  justify-content: space-between;
  max-width: 1000px;
  font-size: 18px;
  font-weight: bold;
  margin-top: 20px;
}

h2 {
  color: #333;
  margin-bottom: 20px;
}
.css-1k8svhy {
  width: 100%; 
  max-width: 300px; 
  height: auto; 
  object-fit: contain;
  margin-top: 120px;
}

</style>