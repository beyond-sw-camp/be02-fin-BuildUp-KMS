<template>
  <div class="css-12gtq4k">
    <div class="css-1k8rayr">
      <div class="css-111ukc0">
        <div class="css-1wlmsap">
          <img src="@/assets/img/002.png" class="css-1k8svhy" />
          <div class="line"></div>
          <div direction="vertical" size="20" class="css-1i0k62c"></div>
          <div
            class="pic-holder"
            @mouseover="showUploadText = true"
            @mouseleave="showUploadText = false"
          >
            <img
              v-if="!selectedProfileImage"
              class="pic"
              :src="defaultProfileImage"
            />
            <img v-else class="pic" :src="selectedProfileImageURL" />
            <input
              class="uploadProfileInput"
              type="file"
              name="profile_pic"
              id="newProfilePhoto"
              accept="image/*"
              style="opacity: 0"
              @change="handleProfileImageChange"
            />
            <label
              v-if="!selectedProfileImage || showUploadText"
              for="newProfilePhoto"
              class="upload-file-block"
            >
              <div class="text-center">
                <div class="mb-2">
                  <i class="fa fa-camera fa-2x"></i>
                </div>
                <div
                  class="text-uppercase"
                  v-if="!selectedProfileImage || showUploadText"
                >
                  프로필 이미지 <br />
                  업로드
                </div>
              </div>
            </label>
          </div>
          <!-----------------프로필 이미지 업로드----------------->
          <div direction="vertical" size="60" class="css-1bisjhm"></div>
          <div class="css-1b8vwo3">이메일</div>
          <div class="css-666rgn">
            <input
              placeholder="이메일을 입력해 주세요."
              type="string"
              autocapitalize="off"
              class="login-custom-input css-ownijh"
              value=""
            />
          </div>
          <div direction="vertical" size="20" class="css-1i0k62c"></div>

          <div class="css-1b8vwo3-1">비밀번호</div>
          <div class="css-666rgn">
            <input
              placeholder="비밀번호를 입력해주세요."
              type="password"
              autocapitalize="off"
              class="login-custom-input css-1f4y3nx"
              value=""
            />
          </div>
          <div direction="vertical" size="20" class="css-1i0k62c"></div>
          <div class="css-1b8vwo3-2">이름</div>
          <div class="css-666rgn">
            <input
              placeholder="이름을 입력해주세요."
              type="string"
              autocapitalize="off"
              class="login-custom-input css-ownijh"
              value=""
            />
          </div>
          <div direction="vertical" size="20" class="css-1i0k62c"></div>

          <div class="css-1b8vwo3-3">닉네임</div>
          <div class="css-666rgn">
            <input
              placeholder="닉네임을 입력해주세요."
              type="string"
              autocapitalize="off"
              class="login-custom-input css-ownijh"
              value=""
            />
          </div>
          <div direction="vertical" size="32" class="css-h23ofx"></div>
          <div class="css-17w7nyr"></div>
          <div class="css-8luw5u">
            <button class="css-27eumk" @click="toggleAllAgreements">
              <svg
                width="20"
                height="20"
                viewBox="0 0 20 20"
                fill="none"
                xmlns="http://www.w3.org/2000/svg"
              >
                <rect
                  x="3.33331"
                  y="3.33331"
                  width="13.3333"
                  height="13.3333"
                  rx="2"
                  :fill="allAgreements ? '#141617' : 'none'"
                  :stroke="allAgreements ? '#141617' : '#9DA7AE'"
                  stroke-width="1.75"
                ></rect>
                <path
                  v-if="allAgreements"
                  d="M6.8327 10.2727L8.91604 12.1667L13.4994 8"
                  stroke="#FFFFFF"
                  stroke-width="1.25"
                  stroke-linecap="round"
                  stroke-linejoin="round"
                ></path>
              </svg>
              전체 약관 동의
            </button>
            <div class="css-1jf7ho2">
              <div
                v-for="(agreement, index) in agreements"
                :key="index"
                class="css-d650kt"
              >
                <button class="css-7hwvbl" @click="toggleAgreement(index)">
                  <svg
                    width="20"
                    height="20"
                    viewBox="0 0 20 20"
                    fill="none"
                    xmlns="http://www.w3.org/2000/svg"
                  >
                    <path
                      v-if="!agreement.checked"
                      d="M3.33301 10L7.49967 14.1667L16.6663 5"
                      stroke="#B4BFC6"
                      stroke-width="1.75"
                      stroke-linecap="round"
                      stroke-linejoin="round"
                    ></path>
                    <path
                      v-if="agreement.checked"
                      d="M3.33301 10L7.49967 14.1667L16.6663 5"
                      stroke="#141617"
                      stroke-width="1.75"
                      stroke-linecap="round"
                      stroke-linejoin="round"
                    ></path>
                  </svg>
                  <div class="css-w2z16z">{{ agreement.label }}</div>
                </button>
                <a class="css-1rvdgmi">보기</a>
              </div>
            </div>
          </div>
          <div direction="vertical" size="40" class="css-ygt1wz"></div>
          <button color="#FFFFFF" class="css-j27xag">가입하기</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "SignupPage",
  data() {
    return {
      defaultProfileImage: require("@/assets/img/profile.jpg"),
      selectedProfileImage: null,
      selectedProfileImageURL: "",
      showUploadText: false,

      isUploading: false,
      isUploaded: false,
      uploadProgress: 0,
      uploadedFile: null,
      errorMessage: "",

      isOpenGuide: false,

      allAgreements: false,
      agreements: [
        { label: "[필수] 만 14세 이상", checked: false },
        { label: "[필수] 서비스 약관 동의", checked: false },
        { label: "[필수] 개인정보처리방침 및 제3자 제공 동의", checked: false },
        { label: "[선택] 광고성 정보 수신 동의", checked: false },
      ],
    };
  },
  mounted() {
    this.$root.hideHeaderAndFooter = true;
  },
  methods: {
    handleProfileImageChange(event) {
      const file = event.target.files[0];
      if (file) {
        this.selectedProfileImage = file;
        this.selectedProfileImageURL = URL.createObjectURL(file);
        this.showUploadText = false; // 이미지가 선택되면 텍스트를 숨깁니다.
      }
    },

    handleFileUpload(event) {
      const file = event.target.files[0];
      const ext = file.name.split(".").pop().toLowerCase();

      if (["gif", "png", "jpg", "jpeg"].indexOf(ext) === -1) {
        this.errorMessage = "이미지 파일이 아닙니다.";
      } else {
        this.errorMessage = "";
        this.isUploading = true;
        const reader = new FileReader();

        reader.onload = () => {
          setTimeout(() => {
            this.uploadedFile = reader.result;
            this.isUploaded = true;
            this.isUploading = false;
          }, 3000);
        };

        reader.onprogress = (e) => {
          if (e.lengthComputable) {
            this.uploadProgress = Math.round((e.loaded / e.total) * 100);
          }
        };

        reader.readAsDataURL(file);
      }
    },
    removeUploadedFile() {
      this.uploadedFile = null;
      this.isUploaded = false;
      this.uploadProgress = 0;
    },

    openGuideModal() {
      this.isOpenGuide = true;
    },
    closeGuideModal() {
      this.isOpenGuide = false;
    },

    toggleAllAgreements() {
      this.allAgreements = !this.allAgreements;
      this.agreements.forEach(
        (agreement) => (agreement.checked = this.allAgreements)
      );
    },
    toggleAgreement(index) {
      this.agreements[index].checked = !this.agreements[index].checked;
      this.allAgreements = this.agreements.every(
        (agreement) => agreement.checked
      );
    },
  },
};
</script>

<style scoped>
body {
  display: block;
  margin: 8px;
}

* {
  line-height: 1.5;
  box-sizing: border-box;
  letter-spacing: normal;
}

* {
  margin: 0;
  line-height: 1.5;
}

body,
html {
  padding: 0;
  margin: 0;
  line-height: 1.5;
  font-family: Pretendard, serif;
}

body {
  height: 100%;
  margin: 0;
  overflow-x: hidden;
  font-size: 1.4rem;
  box-sizing: border-box;
}

element.style {
  overflow: hidden;
}

.css-1k8rayr {
  width: 1000px;
  height: fit-content;
  padding: 0px 24px 60px;
  margin: 60px 0px;
  background: rgb(255, 255, 255);
  border-radius: 12px;
  position: relative;
  box-sizing: border-box;
  display: flex;
  flex-direction: column;
}

.css-111ukc0 {
  width: 450px;
  overflow: visible;
  margin: auto;
  display: flex;
  flex-direction: column;
  -webkit-box-pack: justify;
  justify-content: space-between;
  -webkit-box-flex: 1;
  flex-grow: 1;
}

:not(svg) {
  transform-origin: 0px 0px;
}
svg {
  width: 20;
  height: 20;
  fill: none;
}

svg:not(:root) {
  overflow-clip-margin: content-box;
  overflow: hidden;
}

.css-1k8svhy {
  width: 248px;
  object-fit: contain;
}

.css-profile {
  display: block;
  margin: 0 auto;
  width: 120px;
  object-fit: contain;
}

.css-1bisjhm {
  width: 100%;
  height: 60px;
}

.css-666rgn {
  width: 100%;
  display: flex;
  flex-direction: column;
  -webkit-box-pack: center;
  justify-content: center;
  align-items: flex-start;
  padding: 15px;
  box-sizing: border-box;
  gap: 8px;
  background: rgb(246, 249, 250);
  border-radius: 12px;
  position: relative;
}

input {
  border: none;
  background: none;
  outline: none;
  appearance: none;
}

.css-ownijh {
  display: flex;
  flex-direction: row;
  align-items: center;
  box-sizing: border-box;
  background: rgb(246, 249, 250);
  border-radius: 8px;
  outline: none;
  border: none;
  caret-color: rgb(20, 22, 23);
  font-family: Pretendard;
  font-style: normal;
  font-weight: 600;
  line-height: 22px;
  color: rgb(20, 22, 23);
  font-size: 14px;
  width: 72%;
  height: 21px;
  padding: 0px;
}

input[type="password" i] {
  padding-block: 1px;
  padding-inline: 2px;
}

input:not(
    [type="file" i],
    [type="image" i],
    [type="checkbox" i],
    [type="radio" i]
  ) {
}

.css-1f4y3nx {
  display: flex;
  flex-direction: row;
  align-items: center;
  box-sizing: border-box;
  background: rgb(246, 249, 250);
  border-radius: 8px;
  outline: none;
  border: none;
  caret-color: rgb(20, 22, 23);
  font-family: Pretendard;
  font-style: normal;
  font-weight: 600;
  line-height: 22px;
  color: rgb(20, 22, 23);
  font-size: 14px;
  width: 100%;
  height: 21px;
  padding: 0px;
}

.css-1i0k62c {
  width: 100%;
  height: 20px;
}

.css-ygt1wz {
  width: 100%;
  height: 40px;
}

.css-17w7nyr {
  width: 100%;
  height: 1px;
  background: #eef3f6;
  margin-top: 70px;
}

.css-8luw5u {
  width: 100%;
  display: flex;
  flex-direction: column;
  gap: 20px;
  align-items: flex-start;
}

a,
button {
  text-decoration: none;
  cursor: pointer;
}

button {
  background: unset;
  border: unset;
  padding: unset;
}

.css-27eumk {
  background: none;
  border: none;
  padding: 0px;
  margin-top: 20px;
  appearance: none;
  cursor: pointer;
  display: flex;
  gap: 12px;
  align-items: center;
  font-family: Pretendard;
  font-style: normal;
  font-weight: 600;
  font-size: 14px;
  line-height: 22px;
  color: rgb(20, 22, 23);
}

.css-1jf7ho2 {
  display: flex;
  flex-direction: column;
  gap: 12px;
  width: 100%;
}

.css-d650kt {
  width: 100%;
  display: flex;
  -webkit-box-pack: justify;
  justify-content: space-between;
}

.css-7hwvbl {
  background: none;
  border: none;
  padding: 0px;
  margin: 0px;
  font-family: inherit;
  font-size: inherit;
  color: inherit;
  appearance: none;
  display: flex;
  gap: 12px;
  -webkit-box-align: center;
  align-items: center;
  cursor: pointer;
}

/*----------체크박스 추가------------*/

svg {
  width: 20;
  height: 20;
  fill: none;
}

.css-w2z16z {
  font-family: Pretendard;
  font-style: normal;
  font-weight: 400;
  font-size: 13px;
  line-height: 21px;
  color: rgb(129, 137, 143);
}

.css-1rvdgmi {
  font-family: Pretendard, -apple-system, “system-ui”, "Malgun Gothic",
    "맑은 고딕", sans-serif;
  font-style: normal;
  font-weight: 500;
  font-size: 13px;
  line-height: 150%;
  text-align: center;
  text-decoration-line: underline;
  color: rgb(129, 137, 143);
  cursor: pointer;
  text-decoration: underline; /* 밑줄 추가 */
  cursor: pointer; /* 마우스 호버 시 손가락 커서로 변경 */
}

.line {
  position: relative;
  height: 1px;
  width: 100%;
  margin: 36px 0;
  background-color: #d4d4d4;
}

/* 흐린 선 */
.line::before {
  content: "일반회원으로 가입하기";
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  background-color: #fff;
  color: #8b8b8b;
  padding: 0 15px;
  text-align: center;
  font-size: 14px; /* 글씨 크기 조정 */
  line-height: 1.4; /* 줄 간격 조정 */
  font-weight: bold;
}

.css-j27xag {
  border: none;
  cursor: pointer;
  width: 99.5%;
  height: 56px;
  background: #541d7a;
  font-family: Pretendard;
  font-style: normal;
  font-weight: 600;
  font-size: 16px;
  line-height: 26px;
  color: rgb(255, 255, 255);
  border-radius: 60px;
  display: flex;
  -webkit-box-pack: center;
  justify-content: center;
  -webkit-box-align: center;
  align-items: center;
  gap: 8px;
}

.css-1wlmsap {
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: center;
  width: 100%;
  padding-top: 60px;
  box-sizing: border-box;
  max-width: 1500px; /* 또는 다른 원하는 최대 가로 사이즈 설정 */
  margin: 0 auto; /* 화면 중앙에 정렬하기 위해 추가 */
}

.css-12gtq4k {
  z-index: 22;
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 100%;
  height: 100%;
  overflow: scroll;
  display: flex;
  -webkit-box-pack: center;
  justify-content: center;
  align-items: flex-start;
  background: rgb(246, 249, 250);
}

.css-16q6yfy * {
  word-break: normal;
  white-space: nowrap;
  box-sizing: border-box;
}

/* 이미지 첨부 버튼 */

/*---------------------------------*/

.css-1b8vwo3::after {
  position: relative;
  left: 2px;
  top: -2px;
  content: "*";
  color: rgb(255, 114, 98);
}
.css-1b8vwo3-1::after {
  position: relative;
  left: 2px;
  top: -2px;
  content: "*";
  color: rgb(255, 114, 98);
}
.css-1b8vwo3-2::after {
  position: relative;
  left: 2px;
  top: -2px;
  content: "*";
  color: rgb(255, 114, 98);
}
.css-1b8vwo3-3::after {
  position: relative;
  left: 2px;
  top: -2px;
  content: "*";
  color: rgb(255, 114, 98);
}

.css-1b8vwo3 {
  font-size: 14px;
  color: rgb(85, 89, 105);
  margin-bottom: 8px;
  position: relative;
  font-weight: bold;
  padding-right: 389px;
}

.css-1b8vwo3-1 {
  font-size: 14px;
  color: rgb(85, 89, 105);
  margin-top: 8px;
  margin-bottom: 8px;
  position: relative;
  font-weight: bold;
  padding-right: 374px;
}

.css-1b8vwo3-2 {
  font-size: 14px;
  color: rgb(85, 89, 105);
  margin-top: 8px;
  margin-bottom: 8px;
  position: relative;
  font-weight: bold;
  padding-right: 400px;
}

.css-1b8vwo3-3 {
  font-size: 14px;
  color: rgb(85, 89, 105);
  margin-top: 8px;
  margin-bottom: 8px;
  position: relative;
  font-weight: bold;
  padding-right: 386px;
}

.css-1b8vwo3-new {
  font-size: 14px;
  color: rgb(85, 89, 105);
  margin-bottom: 8px;
  position: relative;
  font-weight: bold;
  margin-top: 30px;
  padding-right: 170px;
  cursor: pointer;
}

.text {
  text-decoration: underline;
}
/*-------------프로필 이미지 업로드---------------*/
.h1 {
  text-align: center;
  font-size: 1.5rem;
  margin-bottom: 2rem;
}
.profile-pic-wrapper {
  height: 100vh;
  width: 100%;
  position: relative;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.pic-holder {
  text-align: center;
  position: relative;
  border-radius: 50%;
  width: 150px;
  height: 150px;
  overflow: hidden;
  display: flex;
  justify-content: center;
  align-items: center;
  margin-bottom: 20px;
}

.pic-holder .pic {
  height: 100%;
  width: 100%;
  -o-object-fit: cover;
  object-fit: cover;
  -o-object-position: center;
  object-position: center;
}

.pic-holder .upload-file-block,
.pic-holder .upload-loader {
  position: absolute;
  top: 0;
  left: 0;
  height: 100%;
  width: 100%;
  background-color: rgba(90, 92, 105, 0.7);
  color: #f8f9fc;
  font-size: 12px;
  font-weight: 600;
  opacity: 0;
  display: flex;
  align-items: center;
  justify-content: center;
  transition: all 0.2s;
}

.pic-holder .upload-file-block {
  cursor: pointer;
}

.pic-holder:hover .upload-file-block,
.uploadProfileInput:focus ~ .upload-file-block {
  opacity: 1;
}

.pic-holder.uploadInProgress .upload-file-block {
  display: none;
}

.pic-holder.uploadInProgress .upload-loader {
  opacity: 1;
}

/* Snackbar css */
.snackbar {
  visibility: hidden;
  min-width: 250px;
  background-color: #333;
  color: #fff;
  text-align: center;
  border-radius: 2px;
  padding: 16px;
  position: fixed;
  z-index: 1;
  left: 50%;
  bottom: 30px;
  font-size: 14px;
  transform: translateX(-50%);
}

.snackbar.show {
  visibility: visible;
  -webkit-animation: fadein 0.5s, fadeout 0.5s 2.5s;
  animation: fadein 0.5s, fadeout 0.5s 2.5s;
}
.text-uppercase {
  font-size: 16px;
}
@-webkit-keyframes fadein {
  from {
    bottom: 0;
    opacity: 0;
  }
  to {
    bottom: 30px;
    opacity: 1;
  }
}

@keyframes fadein {
  from {
    bottom: 0;
    opacity: 0;
  }
  to {
    bottom: 30px;
    opacity: 1;
  }
}

@-webkit-keyframes fadeout {
  from {
    bottom: 30px;
    opacity: 1;
  }
  to {
    bottom: 0;
    opacity: 0;
  }
}

@keyframes fadeout {
  from {
    bottom: 30px;
    opacity: 1;
  }
  to {
    bottom: 0;
    opacity: 0;
  }
}
/*-------------프로필 이미지 업로드---------------*/
</style>
