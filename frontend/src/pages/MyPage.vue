<template>
  <div id="__next">
    <div class="css-1hnxdb7">
      <div class="css-mbwamd">
        <div class="css-1b9to7p">
          <div class="css-vsssfb">
            <div class="css-110bgim">
              <div class="css-28nsux">
                <div class="css-titlegms">계정 설정</div>
                <div class="css-19831hi">프로필</div>
                <div class="css-nw8p9d" @click="$router.push('/active')">나의 활동</div>
              </div>
            </div>
          </div>
          <div class="css-1rw3qt4">
            <div class="css-vmaf1p">
              <div class="css-1fhge30">
                <div class="css-aw18wm">
                  <span
                    style="
                      box-sizing: border-box;
                      display: block;
                      overflow: hidden;
                      width: initial;
                      height: initial;
                      background: none;
                      opacity: 1;
                      border: 0px;
                      margin: 0px;
                      padding: 0px;
                      position: absolute;
                      inset: 0px;
                    "
                  >
                    <img
                      src="../assets/img/bbb.png"
                      decoding="async"
                      data-nimg="fill"
                      sizes="100vw"
                      style="
                        position: absolute;
                        inset: 0px;
                        box-sizing: border-box;
                        padding: 0px;
                        border: none;
                        margin: auto;
                        display: block;
                        width: 0px;
                        height: 0px;
                        min-width: 100%;
                        max-width: 100%;
                        min-height: 100%;
                        max-height: 100%;
                      "
                    />
                  </span>
                </div>
                <div class="Edit_uploadButton__WfnY3">
                  <div
                    class="Button_button___Dadr Button_secondary__A1XYJ Button_medium__BjTrN"
                  >
                    <div class="css-1sika4i">
                      ⚙ 이미지 변경
                      <input type="file" accept="image/*" />
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="Edit_variables__S8TBF">
              <div
                class="InputBase_inputBase__f8LNN nickname"
                style="width: unset"
              >
                <p class="HelperText_helpText__qK_Jm_PASS">닉네임</p>
                <label class="inputField_label__y8y2P">
                  <input
                    type="text"
                    name="nickname"
                    class="inputField_input__004jm inputField_withLabel__z5_la"
                    placeholder="닉네임"
                    value=""
                  />
                  <div
                    class="InputTextField_right__aDZX4 InputTextField_lengthCount__AGkOH"
                  >
                    <span class="InputTextField_currentLength__ok03_"> 0 </span>
                    / 16
                  </div>
                </label>
                <p class="HelperText_helpText__qK_Jm">
                  한글 8자, 영문 및 숫자 16자까지 혼용할 수 있어요.
                </p>
                <div class="Edit_submit__FGvIA">
                  <button
                    class="Button_button___Dadr Button_primary__BxDoK"
                    type="submit"
                  >
                    <div class="Button_contentWrapper__ek4Um">변경 하기</div>
                  </button>
                </div>
                <p class="HelperText_helpText__qK_Jm_PASS">비밀번호 변경</p>
                <div class="PasswordChange_container__h0ww6">
                  <div class="PasswordChange_input__RuHYP">
                    <div
                      class="InputBase_inputBase__f8LNN"
                      style="width: unset"
                    >
                      <label class="inputField_label__y8y2P">
                        <input
                          type="text"
                          name="currentPassword"
                          class="inputField_input__004jm inputField_password__li_TF"
                          placeholder="현재 비밀번호"
                          value=""
                        />
                      </label>
                      <p class="HelperText_helpText__qK_Jm">
                        기존 비밀번호 그대로 입력해주세요.
                      </p>
                    </div>
                  </div>
                  <div class="PasswordChange_input__RuHYP">
                    <div
                      class="InputBase_inputBase__f8LNN"
                      style="width: unset"
                    >
                      <label class="inputField_label__y8y2P">
                        <input
                          type="text"
                          name="currentPassword"
                          class="inputField_input__004jm inputField_password__li_TF"
                          placeholder="새 비밀번호"
                          value=""
                        />
                      </label>
                      <p class="HelperText_helpText__qK_Jm">
                        비밀번호는 영문 대문자, 소문자, 숫자를 포함한 8자 이상,
                        특수문자 (!,@) 만 사용가능
                      </p>
                    </div>
                  </div>
                  <div class="PasswordChange_input__RuHYP">
                    <div
                      class="InputBase_inputBase__f8LNN"
                      style="width: unset"
                    >
                      <label class="inputField_label__y8y2P">
                        <input
                          type="text"
                          name="currentPassword"
                          class="inputField_input__004jm inputField_password__li_TF"
                          placeholder="새 비밀번호 확인"
                          value=""
                        />
                      </label>
                      <p class="HelperText_helpText__qK_Jm">
                        비밀번호를 동일하게 다시 입력해주세요
                      </p>
                    </div>
                  </div>
                  <div class="Edit_submit__FGvIA">
                    <button
                      class="Button_button___Dadr Button_primary__BxDoK"
                      type="submit"
                    >
                      <div class="Button_contentWrapper__ek4Um">
                        비밀번호 변경
                      </div>
                    </button>
                  </div>
                </div>
                <div class="jollkutgi"></div>
                <div class="withdrawal_membership" style="width: unset">
                  <p class="HelperText_helpText__qK_Jm_PASS">회원 탈퇴</p>
                  <div class="Textarea_container__BlWoX" style="height: 19.2">
                    회원 탈퇴일로부터 계정과 닉네임을 포함한 계정
                    정보(아이디/이메일/닉네임)는 개인 정보 처리방침에 따라
                    60일간 보관(잠김)되며, 60일 경과된 후에는 모든 개인 정보는
                    완전히 삭제되며 더 이상 복구할 수 없게 됩니다. 작성된
                    게시물은 삭제되지 않으며, 익명처리 후 Bootshelf로 소유권이
                    귀속됩니다.
                  </div>
                  <span class="checkbox">
                    <input type="checkbox" />
                    <label for="checkbox"
                      >회원 탈퇴에 관한 정책을 읽었으며, 이에 동의합니다.</label
                    >
                  </span>
                  <div class="withdrawal_membership_buttonWrapper_kkkkk">
                    <button
                      class="Button_button___Dadr Button_primary__BxDoK"
                      type="submit"
                    >
                      <div class="Button_contentWrapper__ek4Um">회원 탈퇴</div>
                    </button>
                  </div>
                </div>
              </div>
            </div>
            <div class="css-1csvk83">
              <div class="css-k59gj9">
                <!--MyPageBoardComponent 자리-->
                <div>
                  <MyPageBoardComponent />
                  <MyPageBoardComponent />
                  <MyPageBoardComponent />
                  <MyPageBoardComponent />
                </div>
                <!--MyPageBoardComponent 자리-->
              </div>
            </div>
            <!--페이지네이션 자리-->
          </div>
        </div>
      </div>
    </div>
    <!--푸터자리-->
  </div>
</template>

<script>
export default {
  name: "MyPage",
  components: {},
};
</script>

<style scoped>
.css-mbwamd {
  width: 100%;
  background-color: rgb(255, 255, 255);
  display: none;
}
@media (min-width: 820px) {
  .css-mbwamd {
    display: flex;
  }
}
.css-1b9to7p {
  width: 942px;
  padding-top: 60px;
  margin: 0px auto;
  display: flex;
  flex-direction: row;
  background-color: rgb(255, 255, 255);
  gap: 60px;
}
.css-1rw3qt4 {
  width: 100%;
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  margin-top: 95px;
  -webkit-flex-direction: column;
  -ms-flex-direction: column;
  flex-direction: column;
  background-color: #f6f9fa;
  border: 0.1rem solid rgb(221, 222, 228);
  border-radius: 1.5rem;
  padding: 2rem 2.5rem;
  margin-bottom: 4rem;
}
@media (min-width: 820px) {
  .css-1rw3qt4 {
    gap: 16px;
    background-color: #ffffff;
    min-height: 800px;
  }
}
.css-vmaf1p {
  width: 100%;
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: column;
  -webkit-flex-direction: row;
  -ms-flex-direction: row;
  flex-direction: row;
  gap: 12px;
  padding: 24px 16px;
  background-color: #ffffff;
  margin-bottom: 20px;
}
@media (min-width: 820px) {
  .css-vmaf1p {
    padding: 0;
  }
}
.css-1fhge30 {
  display: flex;
  flex-direction: row;
  -webkit-box-align: center;
  align-items: center;
  gap: 20px;
  padding: 0 20px;
}
.css-aw18wm {
  width: 100px;
  height: 100px;
  position: relative;
  border-radius: 100%;
  overflow: hidden;
}
.css-5zcuov {
  display: flex;
  flex-direction: row;
  gap: 4px;
  -webkit-box-align: center;
  align-items: center;
}
.css-164vtnj {
  width: 100%;
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  -webkit-flex-direction: row;
  -ms-flex-direction: row;
  flex-direction: row;
  height: 52px;
  background-color: #ffffff;
}
@media (min-width: 820px) {
  .css-164vtnj {
    border-bottom: 1px solid #e4ebf0;
    gap: 18px;
  }
}
.Edit_uploadButton__WfnY3 {
  position: relative;
  overflow: hidden;
}
.Button_button___Dadr Button_secondary__A1XYJ Button_medium__BjTrN {
  width: 50%;
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  -webkit-flex-direction: row;
  -ms-flex-direction: row;
  flex-direction: row;
  height: 30px;
  background-color: #ffffff;
  border-color: #3a3e41;
  font-size: 15px;
}
.Button_button___Dadr.Button_secondary__A1XYJ {
  font-size: 12px;
  font-weight: bold;
  color: rgb(110, 109, 113);
  background-color: var(--transparency-gray-00-opacity-40);
  padding: 1px 8px 5px 8px;
  border: 0.1rem solid rgba(136, 132, 132, 0);
  border-radius: 5px;
  min-width: 10px;
}
.css-cqjiz9 {
  width: 50%;
  height: 52px;
  border-bottom: none;
  font-family: Pretendard;
  font-size: 14px;
  font-weight: 500;
  line-height: 52px;
  text-align: center;
  color: #9da7ae;
}
@media (min-width: 820px) {
  .css-cqjiz9 {
    width: 57px;
    cursor: pointer;
  }
}
.css-2120cz {
  width: 50%;
  height: 52px;
  border-bottom: 2px solid #3a3e41;
  font-family: Pretendard;
  font-size: 14px;
  font-weight: 700;
  line-height: 52px;
  text-align: center;
  color: #3a3e41;
}
@media (min-width: 820px) {
  .css-2120cz {
    width: 57px;
    cursor: pointer;
  }
}
.css-1csvk83 {
  width: 100%;
  display: flex;
  flex-direction: column;
  gap: 4px;
  background-color: rgb(246, 249, 250);
}
@media (min-width: 820px) {
  .css-1csvk83 {
    background-color: rgb(255, 255, 255);
  }
}
.css-vsssfb {
  display: flex;
  flex-direction: column;
  gap: 32px;
  position: sticky;
  top: 10rem;
  max-height: 490px;
  transition: all 0.4s ease 0s;
}
.css-110bgim {
  width: 180px;
  display: flex;
  flex-direction: column;
  gap: 16px;
  background-color: rgb(255, 255, 255);
  flex-shrink: 0;
}
.css-28nsux {
  display: flex;
  flex-direction: column;
  gap: 8px;
  width: 100%;
}
.css-19831hi {
  width: 100%;
  border-radius: 8px;
  padding: 0px 50px 0px 16px;
  background-color: rgba(242, 246, 248, 0);
  height: 48px;
  font-family: Pretendard;
  font-style: normal;
  font-size: 23px;
  line-height: 48px;
  font-weight: 700;
  color: rgb(84, 29, 112);
  cursor: pointer;
  text-align: right;
}
.css-nw8p9d {
  width: 100%;
  border-radius: 8px;
  padding: 0px 50px 0px 16px;
  background-color: rgba(255, 255, 255, 0);
  height: 48px;
  font-family: Pretendard;
  font-style: normal;
  font-size: 23px;
  line-height: 48px;
  font-weight: 700;
  color: rgba(157, 167, 174, 0.758);
  cursor: pointer;
  text-align: right;
}
.css-titlegms {
  width: 100%;
  border-radius: 8px;
  padding: 0px 16px;
  background-color: rgba(255, 255, 255, 0);
  height: 48px;
  font-family: Pretendard;
  font-style: normal;
  font-size: 32px;
  line-height: 48px;
  font-weight: 400;
  color: rgba(0, 0, 0, 0.905);
  font-weight: bold;
  cursor: pointer;
}
*,
::after,
::before {
  box-sizing: border-box;
}
* {
  margin: 0;
  line-height: 1.5;
}
.css-1sika4i {
  text-align: center;
}
.Edit_uploadButton__WfnY3 input {
  cursor: pointer;
  position: absolute;
  left: 0;
  top: 0;
  transform: scale(3);
  opacity: 0;
}
.HelperText_helpText__qK_Jm_PASS{
  font-family: pretended;
  font-style: normal;
  font-size: 20px;
  font-weight: bold;
  margin-bottom: 10px;
}
.inputField_label__y8y2P {
    position: relative;
    display: flex;
    align-items: center;
    width: 100%;
    padding: 0 16px 0 3px;
    border-radius: 0.8rem;
    border: 0.1rem solid rgba(51, 50, 54, 0.186);
    box-sizing: border-box;
    transition: background-color .2s ease-in-out, border-color .2s ease-in-out;
    margin-bottom: 10px;
}
.inputField_input__004jm {
    font-size: 15px;
    flex: 1 1;
    min-width: 0.1rem;
    text-overflow: ellipsis;
    color: rgba(51, 50, 54, 0.919);
    height: 2.8rem;
    margin: 0.4rem 0.4rem;
    transition: color .2s ease-in-out;
    border-color: #ffffff00;
}
.InputTextField_lengthCount__AGkOH {
    font-size: 10px;
    font-weight: bold;
    color: rgb(51, 50, 54);
}
.InputTextField_lengthCount__AGkOH .InputTextField_currentLength__ok03_ {
    color: rgb(84, 29, 112);
    font-weight: bold;
}
.HelperText_helpText__qK_Jm {
    font-size: 12px;
    margin-left: 10px;
    margin-bottom: 10px;
    text-align: left;
    color: rgba(136, 132, 132, 0.599);
    transition: color .2s ease-in-out;
    white-space: pre-line;
}
.Button_button___Dadr.Button_primary__BxDoK {
    font-size: 12px;
    color: rgb(110, 109, 113);
    font-weight: bold;
    padding: 3px 5px;
    border: 0.1rem solid rgba(136, 132, 132, 0);
    border-radius: 5px;
    min-width: 15px;
    background-color: rgba(84, 29, 112, 0.071);
    margin-bottom: 10px;
}
@media (min-width: 48em)
{
  .Edit_submit__FGvIA {
    display: flex;
    justify-content: flex-end;
}
}
[type=button]:not(:disabled), [type=reset]:not(:disabled), [type=submit]:not(:disabled), button:not(:disabled) {
    cursor: pointer;
}
.Textarea_container__BlWoX {
    font-size: 15px;
    color: rgba(136, 132, 132, 0.811);
    border: 0.1rem solid #dde0ea;
    border-radius: 0.8rem;
    padding: 15px 20px;
    margin-bottom: 5px;
}
.checkbox {
    margin-left: 10px;
    font-size: 12px;
    color: rgb(110, 109, 113);
    font-weight: bold;
    text-align: center;
}
@media (min-width: 48em)
{
  .withdrawal_membership_buttonWrapper_kkkkk {
    display: flex;
    justify-content: flex-end;
}
}
.jollkutgi {
    padding-bottom: 10px;
    border-bottom: 1px solid rgba(110, 109, 113, 0.215);
    margin-bottom: 20px;
}
</style>
