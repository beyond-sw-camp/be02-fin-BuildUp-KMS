<template>
  <a :href="'/review/' + reviews.reviewIdx">
    <div class="css-aw1sgr">
      <!--게시글 본문-->
      <div class="css-kem115">
        <div class="css-12i5occ">
          <div class="css-1jibmi3">
            <!--게시글 타이틀-->
            <div class="css-cp47oo">
              {{ reviews.reviewTitle }}
            </div>
            <div class="css-14bssip">
              {{ reviews.reviewContent }}
            </div>
          </div>
          <div class="css-sebsp7"></div>
          <!--태그 컴포넌트 자리-->
        </div>
        <div class="css-bt1qy"></div>
        <img
          v-if="reviews.reviewImage"
          :src="reviews.reviewImage"
          class="css-17s6wd5"
        />
      </div>
      <!--여기서부터는 하단 아이디,추천수..등등-->
      <div class="css-99cwur">
        <!--하단 프로필-->
        <div class="css-1fhge30">
          <div class="css-aw18wm">
            <!-- 아이콘-->
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
                sizes="100vw"
                :src="reviews.profileImage"
                decoding="async"
                data-nimg="fill"
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
          <div class="css-5zcuov">
            <div class="css-1sika4i">
              {{ reviews.userNickName }}
            </div>
            <div class="css-1tify6w">
              <svg
                width="2"
                height="2"
                viewBox="0 0 2 2"
                fill="none"
                xmlns="http://www.w3.org/2000/svg"
              >
                <circle cx="1" cy="1" r="1" fill="#9DA7AE"></circle>
              </svg>
            </div>
            <div class="css-1ry6usa">
              {{ this.$moment.utc(reviews.createdAt).local().format('YYYY-MM-DD HH:mm:ss') }}
            </div>
          </div>
          <div class="css-5zcuovs">
            <div class="css-1sika4is">
              {{ reviews.courseName }}
            </div>
          </div>
          <div class="css-5zcuovss">
            <div class="css-1sika4iss">
              평점 : {{ reviews.courseEvaluation }} 점
            </div>
          </div>
        </div>
        <!-- 여기서부터 추천수 댓글수 등등 표시-->
        <div class="css-o01lup">
          <div class="css-ts29it">
            <div class="css-1ry6usa">댓글</div>
            <div class="css-1ry6usa">
              {{ reviews.commentCnt }}
            </div>
          </div>
          <div clss="css-dbc8ke">・</div>
          <div class="css-ts29it">
            <div class="css-1ry6usa">추천</div>
            <div class="css-1ry6usa">{{ reviews.upCnt }}</div>
          </div>
          <div clss="css-dbc8ke">・</div>
          <div class="css-ts29it">
            <div class="css-1ry6usa">조회</div>
            <div class="css-1ry6usa">{{ reviews.viewCnt }}</div>
          </div>
        </div>
      </div>
    </div>
  </a>
  <!--모바일 추천수-->
  <div class="css-k9ergi">
    <div class="css-192oc4s">
      <div class="css-ts29it">
        <div class="css-hr47l6">
          <svg
            width="16"
            height="16"
            viewBox="0 0 16 16"
            fill="none"
            xmlns="http://www.w3.org/2000/svg"
          >
            <path
              d="M8 13.999L7.364 14.3965C7.50106 14.6158 7.74141 14.749 8 14.749C8.25859 14.749 8.49895 14.6158 8.636 14.3965L8 13.999ZM9.47083 11.6457L8.83483 11.2482L9.47083 11.6457ZM3.33333 3.41565H12.6667V1.91565H3.33333V3.41565ZM13.25 3.99898V9.99898H14.75V3.99898H13.25ZM2.75 9.99898V3.99898H1.25V9.99898H2.75ZM8.636 13.6015L7.16517 11.2482L5.89317 12.0431L7.364 14.3965L8.636 13.6015ZM5.96384 10.5823H3.33333V12.0823H5.96384V10.5823ZM12.6667 10.5823H10.0362V12.0823H12.6667V10.5823ZM8.83483 11.2482L7.364 13.6015L8.636 14.3965L10.1068 12.0431L8.83483 11.2482ZM10.0362 10.5823C9.54771 10.5823 9.09371 10.8339 8.83483 11.2482L10.1068 12.0431C10.0916 12.0675 10.0649 12.0823 10.0362 12.0823V10.5823ZM1.25 9.99898C1.25 11.1496 2.18274 12.0823 3.33333 12.0823V10.5823C3.01117 10.5823 2.75 10.3211 2.75 9.99898H1.25ZM13.25 9.99898C13.25 10.3211 12.9888 10.5823 12.6667 10.5823V12.0823C13.8173 12.0823 14.75 11.1496 14.75 9.99898H13.25ZM12.6667 3.41565C12.9888 3.41565 13.25 3.67682 13.25 3.99898H14.75C14.75 2.84839 13.8173 1.91565 12.6667 1.91565V3.41565ZM7.16517 11.2482C6.90629 10.8339 6.45229 10.5823 5.96384 10.5823V12.0823C5.9351 12.0823 5.9084 12.0675 5.89317 12.0431L7.16517 11.2482ZM3.33333 1.91565C2.18274 1.91565 1.25 2.84839 1.25 3.99898H2.75C2.75 3.67682 3.01117 3.41565 3.33333 3.41565V1.91565Z"
              fill="#B4BFC6"
            ></path>
            <rect
              x="4.58203"
              y="6.25"
              width="1.5"
              height="1.5"
              rx="0.75"
              fill="#B4BFC6"
            ></rect>
            <rect
              x="7.25"
              y="6.25"
              width="1.5"
              height="1.5"
              rx="0.75"
              fill="#B4BFC6"
            ></rect>
            <rect
              x="9.91797"
              y="6.25"
              width="1.5"
              height="1.5"
              rx="0.75"
              fill="#B4BFC6"
            ></rect>
          </svg>
        </div>
        <div class="css-i21m7n">{{ reviews.commentCnt }}</div>
      </div>
    </div>
    <div class="css-192oc4s">
      <div class="css-ts29it">
        <div class="css-hr47l6">
          <svg
            width="16"
            height="16"
            viewBox="0 0 16 16"
            fill="none"
            xmlns="http://www.w3.org/2000/svg"
          >
            <rect
              x="2"
              y="6.66565"
              width="1.33333"
              height="6"
              stroke="#B4BFC6"
              stroke-width="1.5"
              stroke-linejoin="round"
            ></rect>
            <path
              d="M9.1972 2.16336L8.62103 1.68322L9.1972 2.16336ZM10.9725 11.9167H6.66667V13.4167H10.9725V11.9167ZM6.75 12V6.96547H5.25V12H6.75ZM13.25 6.66667V8.91304H14.75V6.66667H13.25ZM13.1446 9.24756L11.4503 11.6679L12.6792 12.528L14.3734 10.1078L13.1446 9.24756ZM11.3333 6.75H13.3333V5.25H11.3333V6.75ZM7.19424 5.73845L9.77336 2.6435L8.62103 1.68322L6.04191 4.77817L7.19424 5.73845ZM9.25 2.45402V4.66667H10.75V2.45402H9.25ZM9.54598 2.75C9.38252 2.75 9.25 2.61748 9.25 2.45402H10.75C10.75 1.78906 10.2109 1.25 9.54598 1.25V2.75ZM9.77336 2.6435C9.71713 2.71098 9.63382 2.75 9.54598 2.75V1.25C9.18866 1.25 8.84979 1.40872 8.62103 1.68322L9.77336 2.6435ZM11.3333 5.25C11.0112 5.25 10.75 4.98883 10.75 4.66667H9.25C9.25 5.81726 10.1827 6.75 11.3333 6.75V5.25ZM13.25 8.91304C13.25 9.03272 13.2132 9.14951 13.1446 9.24756L14.3734 10.1078C14.6185 9.75758 14.75 9.34048 14.75 8.91304H13.25ZM6.66667 11.9167C6.71269 11.9167 6.75 11.954 6.75 12H5.25C5.25 12.7824 5.88426 13.4167 6.66667 13.4167V11.9167ZM10.9725 13.4167C11.6523 13.4167 12.2893 13.085 12.6792 12.528L11.4503 11.6679C11.3412 11.8238 11.1628 11.9167 10.9725 11.9167V13.4167ZM14.75 6.66667C14.75 5.88426 14.1157 5.25 13.3333 5.25V6.75C13.2873 6.75 13.25 6.71269 13.25 6.66667H14.75ZM6.75 6.96547C6.75 6.51709 6.9072 6.0829 7.19424 5.73845L6.04191 4.77817C5.53022 5.3922 5.25 6.16618 5.25 6.96547H6.75Z"
              fill="#B4BFC6"
            ></path>
          </svg>
        </div>
        <div class="css-i21m7n">{{ reviews.upCnt }}</div>
      </div>
    </div>
    <div class="css-192oc4s">
      <div class="css-ts29it">
        <div class="css-hr47l6">
          <svg
            width="16"
            height="16"
            viewBox="0 0 16 16"
            fill="none"
            xmlns="http://www.w3.org/2000/svg"
          >
            <path
              d="M12.6677 14L12.2621 14.6309C12.4929 14.7792 12.7863 14.7898 13.0271 14.6583C13.2679 14.5268 13.4177 14.2744 13.4177 14H12.6677ZM3.33435 14H2.58435C2.58435 14.2744 2.73415 14.5268 2.97496 14.6583C3.21576 14.7898 3.50914 14.7792 3.73992 14.6309L3.33435 14ZM8.00102 11L8.40659 10.3691C8.15953 10.2103 7.8425 10.2103 7.59545 10.3691L8.00102 11ZM4.66768 2.75H11.3344V1.25H4.66768V2.75ZM11.9177 3.33333V14H13.4177V3.33333H11.9177ZM4.08435 14V3.33333H2.58435V14H4.08435ZM13.0733 13.3691L8.40659 10.3691L7.59545 11.6309L12.2621 14.6309L13.0733 13.3691ZM7.59545 10.3691L2.92878 13.3691L3.73992 14.6309L8.40659 11.6309L7.59545 10.3691ZM11.3344 2.75C11.6565 2.75 11.9177 3.01117 11.9177 3.33333H13.4177C13.4177 2.18274 12.4849 1.25 11.3344 1.25V2.75ZM4.66768 1.25C3.51709 1.25 2.58435 2.18274 2.58435 3.33333H4.08435C4.08435 3.01117 4.34552 2.75 4.66768 2.75V1.25Z"
              fill="#B4BFC6"
            ></path>
          </svg>
        </div>
        <div class="css-i21m7n">{{ reviews.scrapCnt }}</div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapStores } from "pinia";
import { useReviewStore } from "../stores/useReviewStore";

export default {
  name: "ReviewBoardComponent",
  props: ["reviews"],
  computed: {
    ...mapStores(useReviewStore),
  },
};
</script>

<style scoped>
.css-k59gj9 {
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  -webkit-flex-direction: column;
  -ms-flex-direction: column;
  flex-direction: column;
  width: 100%;
}
@media (min-width: 820px) {
  .css-aw1sgr {
    padding: 15px 0px;
    border-bottom: 1px solid rgb(228, 235, 240);
    gap: 14px;
    cursor: pointer;
    padding-right: 0px;
  }
}
.css-aw1sgr {
  display: flex;
  flex-direction: column;
  width: 100%;
  padding: 24px 16px;
  background-color: rgb(255, 255, 255);
  gap: 16px;
  padding-right: 0px;
}

.css-aw1sgr:hover {
  background-color: rgb(242, 246, 248);
}
.css-amlmv6 {
  width: 100%;
  display: none;
  flex-direction: row;
  -webkit-box-pack: start;
  justify-content: start;
  -webkit-box-align: center;
  align-items: center;
  gap: 8px;
}
@media (min-width: 820px) {
  .css-amlmv6 {
    display: none;
  }
}
.css-1254q6y {
  display: inline-flex;
  -webkit-box-pack: start;
  justify-content: start;
  -webkit-box-align: center;
  align-items: center;
  gap: 4px;
  padding: 2px 12px 0px;
  border-radius: 40px;
  height: 28px;
  font-size: 12px;
  font-weight: 500;
  line-height: 1;
  color: rgb(58, 62, 65);
  background-color: rgb(238, 243, 246);
}

@media (min-width: 1024px) {
  .css-1254q6y {
    margin-left: 8px;
  }
}
.css-kem115 {
  display: flex;
  flex-direction: row;
  -webkit-box-pack: justify;
  justify-content: space-between;
  -webkit-box-align: center;
  align-items: center;
  gap: 14px;
}
.css-12i5occ {
  display: flex;
  flex-direction: column;
  gap: 8px;
  width: 100%;
}
@media (min-width: 820px) {
  .css-12i5occ {
    gap: 4px;
    width: 100%;
  }
}
.css-1jibmi3 {
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  -webkit-flex-direction: column;
  -ms-flex-direction: column;
  flex-direction: column;
  gap: 4px;
}
.css-cp47oo {
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 1;
  -webkit-box-orient: vertical;
  overflow-wrap: break-word;
  word-break: break-all;
  font-family: Pretendard;
  font-style: normal;
  line-height: 21px;
  color: rgb(20, 22, 23);
  font-size: 14px;
  font-weight: 600;
}

@media (min-width: 820px) {
  .css-cp47oo {
    font-family: Pretendard;
    font-style: normal;
    line-height: 24px;
    color: rgb(20, 22, 23);
    max-width: 83%;
    font-size: 16px;
    font-weight: 600;
  }
}
@media (min-width: 820px) {
  .css-14bssip {
    overflow: hidden;
    word-break: break-all;
    text-overflow: ellipsis;
    font-family: Pretendard;
    font-style: normal;
    font-weight: 400;
    font-size: 14px;
    line-height: 21px;
    color: rgb(95, 102, 107);
  }
}

.css-14bssip {
  overflow: hidden;
  text-overflow: ellipsis;
  word-break: break-all;
  max-width: 850px;
  overflow-wrap: break-word;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  overflow-wrap: break-word;
  font-family: Pretendard;
  font-style: normal;
  font-weight: 400;
  font-size: 14px;
  line-height: 21px;
  color: rgb(95, 102, 107);
  word-break: keep-all;
  height: 45px;
}
.css-sebsp7 {
  display: flex;
  flex-direction: row;
  gap: 4px;
  height: 18px;
}

.css-bt1qy {
  display: flex;
  flex-direction: row;
  gap: 16px;
  height: 46px;
  -webkit-box-align: start;
  align-items: start;
}
.css-1ry6usa {
  font-family: Pretendard;
  font-style: normal;
  font-weight: 400;
  font-size: 12px;
  line-height: 18px;
  color: rgb(157, 167, 174);
}
.css-99cwur {
  display: flex;
  flex-direction: row;
  -webkit-box-pack: justify;
  justify-content: space-between;
  -webkit-box-align: center;
  align-items: center;
  height: 24px;
  margin-right: 20px;
}
.css-1fhge30 {
  display: flex;
  flex-direction: row;
  -webkit-box-align: center;
  align-items: center;
  gap: 8px;
}
.css-aw18wm {
  width: 24px;
  height: 24px;
  position: relative;
  border-radius: 100%;
  overflow: hidden;
}
.css-5zcuov {
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  -webkit-flex-direction: row;
  -ms-flex-direction: row;
  flex-direction: row;
  gap: 4px;
  -webkit-align-items: center;
  -webkit-box-align: center;
  -ms-flex-align: center;
  align-items: center;
}
.css-5zcuovs {
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  -webkit-flex-direction: row;
  -ms-flex-direction: row;
  flex-direction: row;
  gap: 4px;
  -webkit-align-items: center;
  -webkit-box-align: center;
  -ms-flex-align: center;
  align-items: center;
  margin-left: 20px;
  font-family: Pretendard;
  cursor: pointer;
  border-radius: 5px;
  background-color: rgba(84, 29, 112, 0.218);
  padding: 3px 10px;
}
.css-1sika4i {
  font-family: Pretendard;
  font-style: normal;
  font-weight: bold;
  font-size: 12px;
  line-height: 18px;
  color: rgb(58, 62, 65);
}
.css-5zcuovss {
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  -webkit-flex-direction: row;
  -ms-flex-direction: row;
  flex-direction: row;
  gap: 4px;
  -webkit-align-items: center;
  -webkit-box-align: center;
  -ms-flex-align: center;
  align-items: center;
  margin-left: 20px;
  font-family: Pretendard;
  cursor: pointer;
  border-radius: 5px;
  background-color: rgb(0 64 255 / 22%);
  padding: 3px 10px;
}
.css-1sika4is {
  font-family: Pretendard;
  font-style: normal;
  font-weight: bold;
  font-size: 12px;
  line-height: 18px;
  color: rgb(58, 62, 65);
}
.css-1sika4iss {
  font-family: Pretendard;
  font-style: normal;
  font-weight: bold;
  font-size: 12px;
  line-height: 18px;
  color: rgb(58, 62, 65);
}
.css-1tify6w {
  width: 2px;
  height: 2px;
  display: flex;
}
@media (min-width: 820px) {
  .css-o01lup {
    display: flex;
    flex-direction: row;
    -webkit-box-align: center;
    align-items: center;
    gap: 2px;
  }
}
.css-o01lup {
  display: none;
}
.css-ts29it {
  display: flex;
  flex-direction: row;
  -webkit-box-align: center;
  align-items: center;
  gap: 4px;
}
.css-dbc8ke {
  font-family: Pretendard;
  font-style: normal;
  font-weight: 400;
  font-size: 12px;
  line-height: 18px;
  color: rgb(157, 167, 174);
  margin: 0px 4px;
}

@media (min-width: 820px) {
  .css-1vkj2s1 {
    display: none;
  }
}
.css-1vkj2s1 {
  flex-direction: row;
  -webkit-box-align: center;
  align-items: center;
  gap: 4px;
  display: flex;
}
.css-z2xt5y {
  width: 100%;
  display: none;
  flex-direction: row;
  -webkit-box-pack: end;
  justify-content: end;
  -webkit-box-align: center;
  align-items: center;
}

@media (min-width: 820px) {
  .css-z2xt5y {
    display: flex;
  }
}
.css-k9ergi {
  width: 100%;
  height: 42px;
  background-color: rgb(255, 255, 255);
  border-top: 1px solid rgb(228, 235, 240);
  border-bottom: none;
  display: flex;
  flex-direction: row;
  -webkit-box-pack: justify;
  justify-content: space-between;
  padding: 0px 24px;
}
.css-192oc4s {
  display: flex;
  flex-direction: row;
  -webkit-box-align: center;
  align-items: center;
  -webkit-box-pack: center;
  justify-content: center;
  width: 96px;
  height: 42px;
}
.css-hr47l6 {
  width: 16px;
  height: 16px;
}
.css-i21m7n {
  font-family: Pretendard;
  font-style: normal;
  font-weight: 400;
  font-size: 12px;
  line-height: 18px;
  color: rgb(180, 191, 198);
}
@media (min-width: 820px) {
  .css-17t7asl {
    display: -webkit-box;
    display: -webkit-flex;
    display: -ms-flexbox;
    display: flex;
    -webkit-flex-direction: column;
    -ms-flex-direction: column;
    flex-direction: column;
    margin-bottom: 24px;
    gap: 24px;
  }
}
@media (min-width: 820px) {
  .css-k9ergi {
    display: none;
  }
}
@media (min-width: 820px) {
  .css-o01lup {
    display: flex;
    flex-direction: row;
    -webkit-box-align: center;
    align-items: center;
    gap: 2px;
  }
}

@media (min-width: 820px) {
  .css-1vkj2s1 {
    display: none;
  }
}
@media (min-width: 820px) {
  .css-z2xt5y {
    display: flex;
  }
}
.css-17s6wd5 {
  width: 100px;
  height: 92px;
  border-radius: 8px;
  overflow: hidden;
  position: relative;
  display: flex;
  flex-shrink: 0;
  margin-right: 17px;
}

img {
  image-rendering: -moz-crisp-edges;
  image-rendering: -o-crisp-edges;
  image-rendering: -webkit-optimize-contrast;
  -ms-interpolation-mode: nearest-neighbor;
}
a {
  color: rgb(157, 167, 174);
  text-decoration: none;
}

a:hover {
  color: rgb(157, 167, 174);
  text-decoration: none;
}
</style>
