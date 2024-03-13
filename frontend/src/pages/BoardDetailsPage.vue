<template>
  <!-- 내 코드 -->
  <div class="css-1hnxdb7">
    <div class="css-130kwtj">
      <div class="css-1hwcs2h">
        <!-- <div class="css-xbcdvo">자유게시판</div>
          <div class="css-s5p19z">학습 질문</div>
          <div class="css-s5p19z">개발일지</div>
          <div class="css-s5p19z">나의 활동</div> -->
      </div>
    </div>
    <div height="120px" class="css-jbj5u0"></div>
    <div class="css-1g39gls">
      <div class="css-1qjp6uf">
        <div class="css-axl4y">
          <div class="css-1yuvfju">
            <div class="css-k59gj9">
              <div class="css-hmpurq">
                <div class="css-category">지식공유</div>
                <div class="css-kem115">
                  <div class="css-12i5occ">
                    <div class="css-1jibmi3">
                      <div class="css-n7izk0">
                        아파치 + 스프링 부트 내장 톰캣
                      </div>
                    </div>
                    <div class="css-sebsp7"></div>
                  </div>
                  <div class="css-bt1qy">
                    <div class="css-1hqtm5a">
                      <div>
                        <img width="23px" height="23px"
                          src="https://img.icons8.com/pastel-glyph/64/facebook-like--v1.png" alt="facebook-like--v1" />
                        <p style="font-size: 10px; text-align: center">20</p>
                      </div>
                    </div>

                    <div class="css-1hqtm5a">
                      <div>
                        <img width="23px" height="23px" src="https://img.icons8.com/windows/64/bookmark-ribbon--v1.png"
                          alt="bookmark-ribbon--v1" />
                        <p style="font-size: 10px; text-align: center">20</p>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="css-99cwur">
                  <div class="css-1fhge30">
                    <div class="css-aw18wm">
                      <span style="
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
                        "><img sizes="100vw"
                          src="https://blog.kakaocdn.net/dn/5UYz8/btq4diRXkGE/HkHufR4G8X4bIX3h3lNjck/img.jpg"
                          decoding="async" data-nimg="fill" style="
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
                          " /></span>
                    </div>
                    <div class="css-5zcuov">
                      <div class="css-1sika4i">닉네임입니당</div>
                      <div class="css-1tify6w">
                        <svg width="2" height="2" viewBox="0 0 2 2" fill="none" xmlns="http://www.w3.org/2000/svg">
                          <circle cx="1" cy="1" r="1" fill="#9DA7AE"></circle>
                        </svg>
                      </div>
                      <div class="css-1ry6usa">2024년 03월 05일 오후 11:57</div>
                    </div>
                  </div>
                </div>
                <div class="css-z2xt5y"></div>
              </div>
            </div>
            <div class="css-luqgif">
              <div class="editedQ_QContent">
                <p class="css-content">
                  평소에는 사내 서버에 아파치 + 톰캣 연동 후 WAR 파일로 배포를
                  했었는데 이번에 AWS에 서비스를 시작하면서 JAR 파일로
                  배포하기로 하였습니다. 이 경우에도 JAR 파일에 있는 내장 톰캣과
                  아파치를 별도로 연동해야 하나요? 연동해야 한다면, 기존
                  아파치+톰캣 연동 설정과 어떤 점이 달라지나요? 구글링해도
                  레퍼런스가 많이 없는 것 같아 질문드립니다. 감사합니다.
                </p>
              </div>
              <div class="css-iqys2n">
                <!-- 태그 컴포넌트 자리-->
                <TagComponent></TagComponent>
              </div>
            </div>
            <div class="css-1k90lkz">
              <div class="css-lua631">
                <div class="css-13ku1qm">
                  <div style="
                      display: inline-block;
                      width: 24px;
                      height: 24px;
                      text-align: center;
                    ">
                    <img width="16px" height="16px" src="https://img.icons8.com/tiny-glyph/32/000000/comments.png"
                      alt="comments" />
                  </div>
                  댓글 3
                </div>
              </div>
              <div class="css-qzobjv">
                <!-- 댓글 컴포넌트 -->
                <CommentComponent></CommentComponent>
                <div class="css-jpe6jj">
                  <div class="css-3o2y5e">
                    <div width="36px" height="36px" class="css-jg5tbe">
                      <img alt="나의얼굴" width="34px" height="34px"
                        src="https://www.fitpetmall.com/wp-content/uploads/2023/10/image-14.png" />
                    </div>
                  </div>
                  <div class="css-13ljjbe">
                    <div class="commentEditor">
                      <input class="css-001" type="text" placeholder="댓글을 남겨주세요" v-model="boardCommentContent" />
                    </div>
                    <div class="css-btn-div">
                      <button class="css-btn" @click="createBoardComment()">저장</button>
                    </div>
                  </div>
                </div>
              </div>
              <!-- 목록으로 돌아가기 버튼 -->
              <div class="css-back-div">
                <button class="css-board-back">목록가기</button>
              </div>
              <!-- 돌아가기 버튼 끝 -->
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

import CommentComponent from "../components/CommentComponent.vue";
import TagComponent from "../components/TagComponent.vue";
import { useBoardCommentStore } from '@/stores/boardCommentStore';



export default {
  name: "BoardDetailsPage",
  components: {
    CommentComponent,
    TagComponent,
  },
  data() {
    return {
      boardCommentContent: '',
    };
  },
  methods: {

    async createBoardComment(){
      try{
        await useBoardCommentStore().createBoardComment(this.boardCommentContent);
      } catch{
        console.error('댓글 작성 실패:', error);
      }
    }

  }
}


</script>

<style scoped>
body {
  height: 100%;
  margin: 0;
  overflow-x: hidden;
  font-size: 1.4rem;
  box-sizing: border-box;
  background-color: #fff;
}

* {
  margin: 0;
  line-height: 1.5;
  line-height: 1.5;
  box-sizing: border-box;
  letter-spacing: normal;
}

div {
  display: block;
}

html {
  font-size: 10px;
  scroll-behavior: smooth;
  display: block;
}

button {
  background: unset;
  border: unset;
  padding: unset;
}

a {
  text-decoration: none;
  cursor: pointer;
}

.css-1hnxdb7 {
  background-color: #fff;
}

.css-icon {
  font-size: 18px;
  color: #9da7ae;
}

.css-category {
  width: 65px;
  padding: 2px 0;
  border-radius: 40px;
  background-color: #541d70;
  color: #fff;
  font-size: 10px;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 6px;
  font-family: Pretendard;
}

@media (min-width: 820px) {
  .css-1g39gls {
    display: flex;
    flex-direction: row;
    width: 100%;
    gap: 43px;
    max-width: 942px;
    margin: 0 auto;
    padding: 150px 0 120px;
  }
}

.css-1g39gls {
  background-color: #fff;
}

@media (min-width: 820px) {
  .css-1qjp6uf {
    max-width: 680px;
    border-radius: 8px;
    border: 1px solid #e4ebf0;
    background-color: #fff;
    padding: 40px;
    width: 100%;
    margin: auto;
  }
}

.css-1yuvfju {
  margin: auto;
  max-width: 942px;
  width: 100%;
}

.css-k59gj9 {
  display: flex;
  flex-direction: column;
  width: 100%;
}

@media ((min-width: 820px)) {
  .css-hmpurq {
    padding: 24px 0;
    border-bottom: 1px solid #e4ebf0;
    gap: 14px;
  }
}

.css-hmpurq {
  display: flex;
  flex-direction: column;
  width: 100%;
  background-color: #fff;
}

.css-kem115 {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  gap: 16px;
}

@media ((min-width: 820px)) {
  .css-12i5occ {
    gap: 4px;
    width: 100%;
  }
}

.css-12i5occ {
  display: flex;
  flex-direction: column;
}

.css-1jibmi3 {
  display: flex;
  flex-direction: column;
  gap: 4px;
}

@media (min-width: 820px) {
  .css-n7izk0 {
    font-style: normal;
    line-height: 24px;
    color: #141617;
    max-width: 83%;
    font-size: 20px;
    font-weight: 700;
    font-family: Pretendard;
  }
}

.css-n7izk0 {
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  word-wrap: break-word;
  word-break: break-all;
}

.css-sebsp7 {
  display: flex;
  flex-direction: row;
  gap: 4px;
  height: 18px;
}

.css-bt1qy {
  display: flex;
  align-items: start;
  flex-direction: row;
  gap: 16px;
  height: 46px;
}

.css-1hqtm5a {
  cursor: pointer;
  width: 20px;
  height: 20px;
}

.css-99cwur {
  display: flex;
  flex-direction: row;
  align-items: center;
  height: 24px;
  margin: 0;
  line-height: 1.5;
  box-sizing: border-box;
  letter-spacing: normal;
  font-size: 1.4rem;
}

.css-1fhge30 {
  display: flex;
  flex-direction: row;
  align-items: center;
  gap: 8px;
  margin: 0;
  line-height: 1.5;
  box-sizing: border-box;
  letter-spacing: normal;
}

.css-aw18wm {
  width: 24px;
  height: 24px;
  position: relative;
  border-radius: 100%;
  overflow: hidden;
}

.css-5zcuov {
  display: flex;
  flex-direction: row;
  gap: 4px;
  align-items: center;
}

.css-1sika4i {
  font-family: Pretendard;
  font-style: normal;
  font-weight: 400;
  font-size: 12px;
  line-height: 18px;
  color: #3a3e41;
}

.css-1tify6w {
  width: 2px;
  height: 2px;
  display: flex;
}

.css-1ry6usa {
  font-style: normal;
  font-weight: 400;
  font-size: 12px;
  line-height: 18px;
  color: #9da7ae;
}

@media (min-width: 820px) {
  .css-z2xt5y {
    display: flex;
  }
}

.css-z2xt5y {
  flex-direction: row;
  align-items: center;
}

.css-luqgif {
  padding: 39px 20px;
  border-bottom: solid 1px #eaebed;
  background-color: #fff;
  border-top: 1px solid #e4ebf0;
}

.editedQ_QContent {
  min-height: 180px;
}

.css-content {
  line-height: 30px;
  font-size: 14px;
  font-family: Pretendard;
}

.css-iqys2n {
  display: flex;
  align-items: center;
  justify-content: left;
  gap: 12px;
  padding-bottom: 20px;
  padding-top: 80px;
}

.css-1kc14yj {
  cursor: pointer;
  width: 100px;
  padding: 7px 0;
  border-radius: 40px;
  background-color: #eaeaea;
  font-size: 12px;
  font-weight: 600;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 6px;
}

.css-1k90lkz {
  padding: 39px 20px;
  border-bottom: none;
  background-color: #fff;
}

.css-lua631 {
  display: flex;
  flex-direction: row;
  align-items: center;
  margin-bottom: 28px;
}

.css-13ku1qm {
  display: flex;
  align-items: center;
  gap: 6px;
  font-family: Pretendard;
  font-size: 16px;
  font-weight: 700;
  line-height: 1.5;
  text-align: left;
  color: #1c1d1e;
}

.css-qzobjv {
  display: flex;
  flex-direction: column;
  align-items: start;
  justify-content: center;
  gap: 12px;
}

.css-f7no94 {
  display: flex;
  flex-direction: row;
  width: 100%;
  gap: 16px;
}

.css-3o2y5e {
  margin-top: 10px;
  display: block;
}

.css-jg5tbe {
  width: 36px;
  height: 36px;
  border: solid 1px #adb5bd;
  background-color: #f1f1f1;
  border-radius: 100px;
  overflow: hidden;
}

.css-14f8kx2 {
  width: 100%;
  max-width: 545px;
  padding: 20px;
  border-radius: 12px;
  background-color: #f4f5f6;
}

.css-1psklmw {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  margin-bottom: 8px;
}

.css-dyzp2y {
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 4px;
}

.css-dyzp2y-001 {
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 4px;
  margin-left: 200px;
}

.css-wqf8ry {
  font-size: 14px;
  font-weight: 700;
  line-height: 1.5;
  text-align: left;
  color: #1c1d1e;
}

.css-khzu4u {
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 2px 6px;
  border-radius: 4px;
  background-color: #1c1d1e;
  font-family: Pretendard, -apple-system, “system-ui”, "Malgun Gothic",
    "맑은 고딕", sans-serif;
  font-size: 10px;
  font-weight: 700;
  line-height: 1.5;
  color: #fff;
}

.css-emxp16 {
  font-size: 12px;
  font-weight: 500;
  line-height: 1.3;
  text-align: left;
  color: #838689;
}

.css-emxp17 {
  font-size: 12px;
  font-weight: 500;
  line-height: 1.3;
  text-align: left;
  color: #838689;
  cursor: pointer;
}

.css-f7no94 {
  display: flex;
  flex-direction: row;
  width: 100%;
  gap: 16px;
}

.css-3o2y5e {
  margin-top: 10px;
  display: block;
}

.css-1psklmw {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  margin-bottom: 8px;
}

.css-dyzp2y {
  display: flex;
  align-items: center;
  justify-content: start;
  gap: 4px;
}

.css-jpe6jj {
  gap: 16px;
  width: 100%;
  display: flex;
  align-items: start;
  justify-content: center;
}

.css-13ljjbe {
  width: 100%;
  border: 1px solid #eaebed;
  border-radius: 12px;
  height: 90px;
}

.commentEditor {
  display: flex;
  align-items: baseline;
  justify-content: space-between;
}

.css-001 {
  flex: 1;
  margin: 10px;
  outline: 0;
  border: none;
  font-family: Pretendard;
}

.css-btn-div {
  display: flex;
  justify-content: flex-end;
  margin-left: 10px;
}

.css-btn {
  background-color: rgb(52, 152, 219, 0.2);
  font-size: 12px;
  color: #000;
  font-weight: 700;
  width: 70px;
  height: 25px;
  border-radius: 10px;
  float: right;
  margin-right: 10px;
  cursor: pointer;
  font-family: Pretendard;
}

.css-5zcuov {
  display: flex;
  flex-direction: row;
  gap: 4px;
  align-items: center;
}

.css-n4f4vi {
  width: 20px;
  height: 20px;
}

.css-n8w3ba {
  display: flex;
  justify-content: center;
  background-color: #1c1d1e;
  border-radius: 8px;
  padding: 14px;
  font-size: 14px;
  cursor: pointer;
  font-weight: 700;
  color: #f4f5f6;
  gap: 8px;
}

.css-1qam7gv {
  display: flex;
  flex-direction: row;
  gap: 8px;
}

.css-1vdbpsh {
  background-color: #1c1d1e;
  border-radius: 8px;
  text-align: center;
}

.css-board-back {
  cursor: pointer;
  width: 80px;
  height: 50px;
  padding: 15px 0;
  border-radius: 10px;
  background-color: #541d70;
  font-size: 13px;
  display: flex;
  justify-content: center;
  gap: 6px;
  color: #fff;
  font-family: Pretendard;
}

.css-back-div {
  text-align: center;
  margin-top: 50px;
}
</style>
