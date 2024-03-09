<template>
  <div id="__next">
    <div class="css-1hnxdb7">
      <div class="css-mbwamd">
        <div class="css-1b9to7p">
          <div class="css-vsssfb">
            <div class="css-110bgim">
              <div class="css-28nsux">
                <div class="css-titlegms">계정 설정</div>
                <div class="css-nw8p9d">프로필</div>
                <div class="css-19831hi">나의 활동</div>
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
                <div class="css-5zcuov">
                  <div class="css-1sika4i">길 아무개</div>
                </div>
              </div>
            </div>
            <div class="css-164vtnj">
              <div class="css-cqjiz9">스크랩</div>
              <div class="css-2120cz">작성글</div>
              <div class="css-cqjiz9">댓글</div>
              <div class="css-cqjiz9">스터디</div>
            </div>
            <div class="css-1csvk83">
              <div class="css-k59gj9">
                <!--MyPageBoardComponent 자리-->
                <div>
                  <MyPageBoardComponent/>
                  <MyPageBoardComponent/>
                  <MyPageBoardComponent/>
                  <MyPageBoardComponent/>
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
import MyPageBoardComponent from '@/components/MyPageBoardComponent.vue';
export default {
  name: "MyActivePage",
  components: {
    MyPageBoardComponent,
  }
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
  padding: 4rem 4.8rem;
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
  display: flex;
  -webkit-flex-direction: row;
  -ms-flex-direction: row;
  flex-direction: row;
  gap: 12px;
  padding: 24px 16px;
  background-color: #ffffff;
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
  gap: 8px;
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
.css-1sika4i {
  font-family: Pretendard;
  font-style: normal;
  font-weight: 400;
  font-size: 40px;
  line-height: 18px;
  color: rgb(58, 62, 65);
  padding-left: 10px;
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
    color: rgb(84,29,112);
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
*, ::after, ::before {
    box-sizing: border-box;
}
* {
    margin: 0;
    line-height: 1.5;
}
</style>
