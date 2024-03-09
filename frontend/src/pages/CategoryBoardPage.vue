<template>
  <div id="__next">
    <div class="css-1hnxdb7">
      <div class="css-mbwamd">
        <div class="css-1b9to7p">
          <!-- 여기는 왼쪽 사이드바-->
          <div class="css-vsssfb">
            <div class="css-110bgim">
              <div class="css-28nsux">
                <HotTagComponent />
              </div>
            </div>
          </div>
          <!--여기서부터는 게시판 쪽-->
          <div class="css-33zep3">
            <div class="css-17t7asl">
              <div class="css-1jibmi3">
                <div class="css-1mpmq0i">
                  <div class="css-18vdxik">자유게시판</div>
                </div>
                <!--부제-->
                <div class="css-1qzbd5x">부제</div>
              </div>
            </div>
            <!--여기서부터 우리거 적용-->
            <div class="css-1rw3qt4">
              <!--여기가 카테고리-->
              <div class="css-6cwwok">
                <div class="css-1hbxc4s">
                  <div class="css-ogh6wd">
                    <div class="css-1tttep5">
                      <div class="css-1pbcmmt">
                        <div class="css-5ala5m">
                          <div class="css-nmdn6a">카테고리</div>
                          <div class="css-bw1gsw">전체</div>
                        </div>
                        <!--이건 카테고리 드롭다운-->
                        <svg
                          width="16"
                          height="16"
                          viewBox="0 0 16 16"
                          fill="none"
                          xmlns="http://www.w3.org/2000/svg"
                        >
                          <path
                            d="M4.53033 6.13373C4.23744 5.84084 3.76256 5.84084 3.46967 6.13373C3.17678 6.42663 3.17678 6.9015 3.46967 7.19439L4.53033 6.13373ZM8 10.6641L7.46967 11.1944C7.76256 11.4873 8.23744 11.4873 8.53033 11.1944L8 10.6641ZM12.5303 7.19439C12.8232 6.9015 12.8232 6.42663 12.5303 6.13373C12.2374 5.84084 11.7626 5.84084 11.4697 6.13373L12.5303 7.19439ZM3.46967 7.19439L7.46967 11.1944L8.53033 10.1337L4.53033 6.13373L3.46967 7.19439ZM8.53033 11.1944L12.5303 7.19439L11.4697 6.13373L7.46967 10.1337L8.53033 11.1944Z"
                            fill="#9DA7AE"
                          ></path>
                        </svg>
                      </div>
                    </div>
                    <div class="css-g70uuw"></div>
                  </div>
                </div>
              </div>
              <!--여기서 본격 글 리스트-->
              <div class="css-1csvk83">
                <!--게시글 한칸-->
                <div>
                  <CategoryBoardComponent />
                </div>
                <!--게시글 한칸 끝-->
              </div>
            </div>
            <!--페이지네이션-->
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import CategoryBoardComponent from "@/components/CategoryBoardComponent.vue";
import HotTagComponent from "@/components/HotTagComponent.vue";
export default {
  name: "CategoryBoardPage",
  components: {
    CategoryBoardComponent,
    HotTagComponent,
  },
};
</script>

<style scoped>
@media (min-width: 820px) {
  .css-mbwamd {
    display: flex;
  }
}
.css-mbwamd {
  width: 100%;
  background-color: rgb(255, 255, 255);
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
@media (min-width: 820px) {
  .css-33zep3 {
    margin-top: 8rem;
    max-width: 680px;
  }
}
.css-33zep3 {
  margin-top: 8rem;
  width: 100%;
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  -webkit-flex-direction: column;
  -ms-flex-direction: column;
  flex-direction: column;
}
.css-17t7asl {
  width: 100%;
  display: none;
  -webkit-box-pack: justify;
  -webkit-justify-content: space-between;
  justify-content: space-between;
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
.css-1mpmq0i {
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  -webkit-flex-direction: row;
  -ms-flex-direction: row;
  flex-direction: row;
  -webkit-align-items: center;
  -webkit-box-align: center;
  -ms-flex-align: center;
  align-items: center;
  gap: 4px;
  position: relative;
}
.css-18vdxik {
  font-family: Pretendard;
  font-style: normal;
  font-weight: 600;
  font-size: 24px;
  line-height: 34px;
  color: #141617;
  line-height: 33px;
  color: #141617;
}
.css-1qzbd5x {
  font-family: Pretendard;
  font-style: normal;
  font-weight: 500;
  font-size: 14px;
  line-height: 22px;
  color: #141617;
  line-height: 21px;
  color: #9da7ae;
}
.css-1rw3qt4 {
  width: 100%;
  display: flex;
  flex-direction: column;
  background-color: rgb(246, 249, 250);
}
.css-6cwwok {
  background-color: rgb(255, 255, 255);
  width: 100%;
}
.css-1hbxc4s {
  display: flex;
  flex-direction: row;
  gap: 12px;
  padding: 12px 16px;
  -webkit-box-align: center;
  align-items: center;
  position: relative;
}
.css-ogh6wd {
  display: flex;
  flex-direction: row;
  gap: 6px;
  overflow-x: auto;
  white-space: nowrap;
}
.css-1tttep5 {
  position: relative;
  display: flex;
}
.css-1pbcmmt {
  display: flex;
  flex-direction: row;
  padding: 6px 12px;
  -webkit-box-pack: center;
  justify-content: center;
  -webkit-box-align: center;
  align-items: center;
  gap: 4px;
  border-radius: 100px;
  background: rgb(255, 255, 255);
  border: 1px solid rgb(228, 235, 240);
  flex: 0 0 auto;
}
.css-5ala5m {
  display: flex;
  flex-direction: row;
  gap: 4px;
  flex-shrink: 0;
  width: fit-content;
}
.css-nmdn6a {
  font-family: Pretendard;
  font-style: normal;
  font-weight: 500;
  font-size: 13px;
  line-height: 160%;
  color: rgb(58, 62, 65);
  flex-shrink: 0;
  max-width: 120px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
.css-bw1gsw {
  font-family: Pretendard;
  font-style: normal;
  font-weight: bold;
  font-size: 13px;
  line-height: 160%;
  flex-shrink: 0;
  max-width: 120px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  color: rgb(84, 29, 112);
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
  top: 140px;
  max-height: 490px;
  transition: all 0.4s ease 0s;
}
.css-16bft0y {
  position: relative;
  width: 100%;
  display: flex;
  flex-direction: column;
  gap: 12px;
}
.css-804v49 {
  width: 100%;
  height: 48px;
  background-color: rgb(84, 29, 112);
  border-radius: 8px;
  text-align: center;
  font-family: Pretendard;
  font-size: 16px;
  font-weight: 700;
  color: rgb(255, 255, 255);
  line-height: 48px;
  cursor: pointer;
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
</style>
