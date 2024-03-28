<template>
  <div class="loadingio-spinner-spinner" v-if="userStore.isLoading">
    <div class="ldio-f4nnk2ltl0v">
      <div></div>
      <div></div>
      <div></div>
      <div></div>
      <div></div>
      <div></div>
      <div></div>
      <div></div>
      <div></div>
      <div></div>
      <div></div>
      <div></div>
    </div>
  </div>
  <header>
    <div
      class="css-wkr2a"
      :style="{
        borderBottom: showBorder ? '1px solid rgb(228, 235, 240)' : 'none',
      }"
    >
      <div class="css-we70d5">
        <div class="css-un3w91">
          <router-link to="/">
            <img
              src="../assets/img/logo.png"
              v-if="showLogo"
              alt="BOOKSHELF 로고"
              class="css-huvhzc"
            />
          </router-link>
        </div>
        <div class="css-1butcu2" v-if="showMenu">
          <router-link to="/board/knowledge">
            <a>
              <div class="css-1llgdoe">지식공유</div>
            </a>
          </router-link>
          <router-link to="/board/qna">
            <a>
              <div class="css-1llgdoe">Q&A</div>
            </a>
          </router-link>
          <router-link to="/review">
            <a>
              <div class="css-1llgdoe">후기</div>
            </a>
          </router-link>
          <router-link to="/study">
            <a>
              <div class="css-1llgdoe">스터디</div>
            </a>
          </router-link>
        </div>
        <div class="css-1butcu">
          <div>
            <button
              class="css-login-button"
              @click="openLoginModal()"
              v-if="!isAuthenticated"
            >
              로그인
            </button>
            <button
              class="css-signup-button"
              @click="selectSignUp()"
              v-if="!isAuthenticated"
            >
              회원가입
            </button>
            <!-- 로그인 되었을 때 닉네임을 표시 -->
            <div v-if="isAuthenticated" class="user-info">
              <span class="user-nickname">{{ decodedToken.nickName }}</span>
            </div>
          </div>
          <div
            class="css-26c0za"
            v-if="isAuthenticated"
            @click="toggleDropdownMenu"
          >
            <svg
              width="24"
              height="24"
              viewBox="0 0 24 24"
              fill="none"
              xmlns="http://www.w3.org/2000/svg"
            >
              <circle
                cx="12"
                cy="8"
                r="4"
                stroke="#141617"
                stroke-width="2"
              ></circle>
              <path
                d="M4.20703 19.998C5.76317 17.3079 8.67172 15.498 12.003 15.498C15.3343 15.498 18.2428 17.3079 19.799 19.998"
                stroke="#141617"
                stroke-width="2"
                stroke-linecap="round"
              ></path>
            </svg>
          </div>
          <div class="css-1fgr4mj" v-if="isDropdownOpen">
            <div class="css-1bkawov">
              <div class="css-gfzvs2">
                <div class="css-6gvm25">
                  <div class="css-myce4z">
                    <div class="css-a72rbc"></div>
                  </div>
                  <div class="css-zqyw9">
                    <span
                      class="css-aw18wm"
                      style="padding: 0px; position: absolute; inset: 0px"
                      ><img
                        sizes="100vw"
                        :src="userProfileImage"
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
                    /></span>
                    <div class="css-156933a" style="font-weight: lighter">
                      {{ decodedToken.name }}
                    </div>
                    <div class="css-10kntrg" style="font-weight: lighter">
                      {{ decodedToken.email }}
                    </div>
                  </div>
                </div>
              </div>
              <div class="css-ss0hg9">
                <div class="css-1o9qtii"></div>
                <div class="css-1s9cv9y">
                  <div>
                    <div>
                      <div class="css-13fd4ke">
                        <div class="css-1jibmi3">
                          <div class="css-16regn8">
                            <svg
                              xmlns="http://www.w3.org/2000/svg"
                              fill="none"
                              viewBox="0 0 24 24"
                              stroke-width="1.5"
                              stroke="currentColor"
                              aria-hidden="true"
                              class="myprofile"
                            >
                              <path
                                stroke-linecap="round"
                                stroke-linejoin="round"
                                d="M17.982 18.725A7.488 7.488 0 0012 15.75a7.488 7.488 0 00-5.982 2.975m11.963 0a9 9 0 10-11.963 0m11.963 0A8.966 8.966 0 0112 21a8.966 8.966 0 01-5.982-2.275M15 9.75a3 3 0 11-6 0 3 3 0 016 0z"
                              ></path>
                            </svg>
                            <div class="css-17gywxx" @click="goMyProfile()">
                              프로필
                            </div>
                          </div>
                        </div>
                      </div>
                      <div class="css-13fd4ke">
                        <div class="css-1jibmi3">
                          <div class="css-16regn8">
                            <svg
                              class="myprofile"
                              width="16"
                              height="16"
                              viewBox="-2 -2 16 16"
                              fill="none"
                              xmlns="http://www.w3.org/2000/svg"
                            >
                              <path
                                d="M7 5.5V7.80769L8.5 9.25"
                                stroke="currentColor"
                                stroke-width="1"
                                stroke-linecap="round"
                                stroke-linejoin="round"
                              ></path>
                              <path
                                d="M1 1.5L1 4.75H1.43783M13 6.25C12.6295 3.29027 10.0949 1 7.0233 1C4.49549 1 2.33137 2.55113 1.43783 4.75M1.43783 4.75H4M12.6088 9.25C11.7152 11.4489 9.55111 13 7.0233 13C3.9517 13 1.41711 10.7097 1.0466 7.75"
                                stroke="currentColor"
                                stroke-width="1"
                                stroke-linecap="round"
                                stroke-linejoin="round"
                              ></path>
                            </svg>
                            <div class="css-17gywxx" @click="goMyPage()">
                              활동내역
                            </div>
                          </div>
                        </div>
                      </div>
                      <div class="css-13fd4ke" v-if="isAdminLogin">
                        <div class="css-1jibmi3">
                          <div class="css-16regn8">
                            <svg
                              xmlns="http://www.w3.org/2000/svg"
                              fill="none"
                              viewBox="0 0 24 24"
                              stroke-width="1.5"
                              stroke="currentColor"
                              aria-hidden="true"
                              class="myprofile"
                            >
                              <path
                                stroke-linecap="round"
                                stroke-linejoin="round"
                                d="M10.343 3.94c.09-.542.56-.94 1.11-.94h1.093c.55 0 1.02.398 1.11.94l.149.894c.07.424.384.764.78.93.398.164.855.142 1.205-.108l.737-.527a1.125 1.125 0 011.45.12l.773.774c.39.389.44 1.002.12 1.45l-.527.737c-.25.35-.272.806-.107 1.204.165.397.505.71.93.78l.893.15c.543.09.94.56.94 1.109v1.094c0 .55-.397 1.02-.94 1.11l-.893.149c-.425.07-.765.383-.93.78-.165.398-.143.854.107 1.204l.527.738c.32.447.269 1.06-.12 1.45l-.774.773a1.125 1.125 0 01-1.449.12l-.738-.527c-.35-.25-.806-.272-1.203-.107-.397.165-.71.505-.781.929l-.149.894c-.09.542-.56.94-1.11.94h-1.094c-.55 0-1.019-.398-1.11-.94l-.148-.894c-.071-.424-.384-.764-.781-.93-.398-.164-.854-.142-1.204.108l-.738.527c-.447.32-1.06.269-1.45-.12l-.773-.774a1.125 1.125 0 01-.12-1.45l.527-.737c.25-.35.273-.806.108-1.204-.165-.397-.505-.71-.93-.78l-.894-.15c-.542-.09-.94-.56-.94-1.109v-1.094c0-.55.398-1.02.94-1.11l.894-.149c.424-.07.765-.383.93-.78.165-.398.143-.854-.107-1.204l-.527-.738a1.125 1.125 0 01.12-1.45l.773-.773a1.125 1.125 0 011.45-.12l.737.527c.35.25.807.272 1.204.107.397-.165.71-.505.78-.929l.15-.894z"
                              ></path>
                              <path
                                stroke-linecap="round"
                                stroke-linejoin="round"
                                d="M15 12a3 3 0 11-6 0 3 3 0 016 0z"
                              ></path>
                            </svg>
                            <div class="css-17gywxx" @click="goAdminPage()">
                              관리자 페이지
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="css-1o9qtii"></div>
                <div class="css-1s9cv9y">
                  <div class="css-ik70s9">
                    <div class="css-1jibmi3">
                      <div class="css-28a73i">
                        <svg
                          xmlns="http://www.w3.org/2000/svg"
                          fill="none"
                          viewBox="0 0 24 24"
                          stroke-width="1.5"
                          stroke="currentColor"
                          aria-hidden="true"
                          class="myprofile"
                        >
                          <path
                            stroke-linecap="round"
                            stroke-linejoin="round"
                            d="M15.75 9V5.25A2.25 2.25 0 0013.5 3h-6a2.25 2.25 0 00-2.25 2.25v13.5A2.25 2.25 0 007.5 21h6a2.25 2.25 0 002.25-2.25V15m3 0l3-3m0 0l-3-3m3 3H9"
                          ></path>
                        </svg>
                        <div class="css-4l7ba3" @click.prevent="logout">
                          로그아웃
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </header>
  <div class="css-1gpya5f" v-show="isLogin">
    <div class="css-4oebd2">
      <div class="css-1n1k95p">
        <div class="css-myjkxi" @click="closeLoginModal()">
          <svg
            width="20"
            height="20"
            viewBox="0 0 20 20"
            fill="none"
            xmlns="http://www.w3.org/2000/svg"
          >
            <path
              d="M4.16663 4.16669L15.8333 15.8334"
              stroke="#3A3E41"
              stroke-width="1.75"
              stroke-linecap="round"
            ></path>
            <path
              d="M15.8334 4.16669L4.16671 15.8334"
              stroke="#3A3E41"
              stroke-width="1.75"
              stroke-linecap="round"
            ></path>
          </svg>
        </div>
      </div>
      <div class="css-1xpr18p">
        <div class="css-kcgo1q">
          <div class="css-gxukgu">
            <br />
            BOOTSHELF
          </div>
          <div direction="vertical" size="12" class="css-j86f64"></div>
          <div class="css-1hal8c">
            개발자 국비지원교육, 부트캠프 과정의 모든 것
          </div>
          <div class="css-1hal8c">BOOTSHELF 회원들과 함께 공유해보세요!</div>
          <div direction="vertical" size="40" class="css-ygt1wz"></div>
          <button
            color="#141617"
            class="css-18wq8ro"
            @click.stop="loginKakao()"
          >
            <svg
              width="18"
              height="18"
              viewBox="0 0 18 18"
              fill="none"
              xmlns="http://www.w3.org/2000/svg"
            >
              <path
                d="M9.00043 2.16699C4.80722 2.16699 1.4082 4.7989 1.4082 8.04735C1.4082 10.161 2.84752 12.0135 5.00935 13.0499C4.85022 13.63 4.43449 15.1538 4.35134 15.4796C4.24812 15.8841 4.50187 15.8785 4.66959 15.7703C4.80005 15.6846 6.74972 14.3855 7.59123 13.8252C8.04854 13.8912 8.51875 13.9263 9.00043 13.9263C13.1937 13.9263 16.5927 11.2944 16.5927 8.04735C16.5927 4.8003 13.1937 2.16699 9.00043 2.16699Z"
                fill="#141617"
              ></path>
            </svg>
            카카오로 3초만에 시작하기
          </button>
          <div direction="vertical" size="16" class="css-10vhklm"></div>
          <button
            color="#141617"
            class="css-fqtlj"
            @click="openEmailLoginModal()"
          >
            이메일로 시작하기
          </button>
          <div direction="vertical" size="32" class="css-h23ofx"></div>
          <span
            color="#81898F"
            class="css-1cap2q"
            style="text-decoration: underline"
            @click="selectSignUp()"
            >회원가입</span
          >
        </div>
        <div class="css-1sfln3b"></div>
      </div>
    </div>
  </div>
  <!----------------여기부러 로그인 모달창 2단계------------------------>
  <div class="css-1gpya5f" v-show="isEmailLogin">
    <div class="css-4oebd2">
      <div class="css-1n1k95p">
        <div class="css-1a96vvi" @click="closeEmailLoginModal()">
          <svg
            width="20"
            height="20"
            viewBox="0 0 20 20"
            fill="none"
            xmlns="http://www.w3.org/2000/svg"
          >
            <path
              d="M9.16699 5L4.16699 10L9.16699 15"
              stroke="#3A3E41"
              stroke-width="1.75"
              stroke-linecap="round"
              stroke-linejoin="round"
            ></path>
            <path
              d="M5 10L15.8333 10"
              stroke="#3A3E41"
              stroke-width="1.75"
              stroke-linecap="round"
              stroke-linejoin="round"
            ></path>
          </svg>
          뒤로
        </div>
        <div class="css-myjkxi" @click="closeAllLoginModal()">
          <svg
            width="20"
            height="20"
            viewBox="0 0 20 20"
            fill="none"
            xmlns="http://www.w3.org/2000/svg"
          >
            <path
              d="M4.16663 4.16669L15.8333 15.8334"
              stroke="#3A3E41"
              stroke-width="1.75"
              stroke-linecap="round"
            ></path>
            <path
              d="M15.8334 4.16669L4.16671 15.8334"
              stroke="#3A3E41"
              stroke-width="1.75"
              stroke-linecap="round"
            ></path>
          </svg>
        </div>
      </div>
      <div class="css-1djguz4">
        <div class="css-yj1ay2">
          <div class="css-bbe5fx">이메일 로그인</div>
          <form id="loginForm" @submit.prevent="onLoginFormSubmit">
            <div direction="vertical" size="40" class="css-ygt1wz"></div>
            <input
              placeholder="이메일 입력"
              type="string"
              autocapitalize="off"
              class="login-custom-input css-krmsb5"
              v-model="email"
            />
            <div direction="vertical" size="12" class="css-j86f64"></div>
            <input
              placeholder="비밀번호 입력"
              type="password"
              autocapitalize="off"
              class="login-custom-input css-krmsb5"
              v-model="password"
            />
            <div direction="vertical" size="40" class="css-ygt1wz"></div>
            <button color="#FFFFFF" class="css-uscuon">로그인</button>
          </form>
        </div>
        <div class="css-1sfln3b">
          <div class="css-1lvb576">
            <span
              color="#81898F"
              class="css-1cap2q"
              style="text-decoration: none"
              @click="selectSignUp()"
              >회원가입</span
            >
            <div class="css-1653a83"></div>
            <span
              color="#81898F"
              class="css-1cap2q"
              style="text-decoration: none"
              @click="openFindEmailModal()"
              >이메일 찾기</span
            >
            <div class="css-1653a83"></div>
            <span
              color="#81898F"
              class="css-1cap2q"
              style="text-decoration: none"
              @click="openFindPasswordModal()"
              >비밀번호 찾기</span
            >
          </div>
        </div>
      </div>
    </div>
  </div>
  <!----------------여기부터 이메일 찾기 모달창-------------------------->
  <div class="css-1gpya5f" v-show="isFindEmail">
    <div class="css-4oebd2">
      <div class="css-1n1k95p">
        <div class="css-1a96vvi" @click="closeFindEmailModal()">
          <svg
            width="20"
            height="20"
            viewBox="0 0 20 20"
            fill="none"
            xmlns="http://www.w3.org/2000/svg"
          >
            <path
              d="M9.16699 5L4.16699 10L9.16699 15"
              stroke="#3A3E41"
              stroke-width="1.75"
              stroke-linecap="round"
              stroke-linejoin="round"
            ></path>
            <path
              d="M5 10L15.8333 10"
              stroke="#3A3E41"
              stroke-width="1.75"
              stroke-linecap="round"
              stroke-linejoin="round"
            ></path>
          </svg>
          뒤로
        </div>
        <div class="css-myjkxi" @click="closeAllLoginModal()">
          <svg
            width="20"
            height="20"
            viewBox="0 0 20 20"
            fill="none"
            xmlns="http://www.w3.org/2000/svg"
          >
            <path
              d="M4.16663 4.16669L15.8333 15.8334"
              stroke="#3A3E41"
              stroke-width="1.75"
              stroke-linecap="round"
            ></path>
            <path
              d="M15.8334 4.16669L4.16671 15.8334"
              stroke="#3A3E41"
              stroke-width="1.75"
              stroke-linecap="round"
            ></path>
          </svg>
        </div>
      </div>
      <div class="css-1djguz4">
        <div class="css-yj1ay2">
          <div class="css-bbe5fx">이메일 찾기</div>
          <form id="findEmailForm"></form>
          <form id="loginForm">
            <div direction="vertical" size="40" class="css-ygt1wz"></div>
            <input
              v-model="name"
              placeholder="이름 입력"
              type="string"
              autocapitalize="off"
              class="login-custom-input css-krmsb5"
            />
            <div direction="vertical" size="12" class="css-j86f64"></div>
            <input
              v-model="nickName"
              placeholder="닉네임 입력"
              type="nickName"
              autocapitalize="off"
              class="login-custom-input css-krmsb5"
            />
            <div direction="vertical" size="40" class="css-ygt1wz"></div>
          </form>
          <button
            @click="sendFindEmail()"
            @keyup.enter="sendFindEmail()"
            color="#FFFFFF"
            class="css-uscuon1"
          >
            확인
          </button>
        </div>
      </div>
    </div>
  </div>
  <div class="css-1gpya5f" v-show="isCheckEmail">
    <div class="css-4oebd2">
      <div class="css-1n1k95p">
        <div class="css-1a96vvi" @click="closeCheckEmailModal()">
          <svg
            width="20"
            height="20"
            viewBox="0 0 20 20"
            fill="none"
            xmlns="http://www.w3.org/2000/svg"
          >
            <path
              d="M9.16699 5L4.16699 10L9.16699 15"
              stroke="#3A3E41"
              stroke-width="1.75"
              stroke-linecap="round"
              stroke-linejoin="round"
            ></path>
            <path
              d="M5 10L15.8333 10"
              stroke="#3A3E41"
              stroke-width="1.75"
              stroke-linecap="round"
              stroke-linejoin="round"
            ></path>
          </svg>
          뒤로
        </div>
        <div class="css-myjkxi" @click="closeAllLoginModal()">
          <svg
            width="20"
            height="20"
            viewBox="0 0 20 20"
            fill="none"
            xmlns="http://www.w3.org/2000/svg"
          >
            <path
              d="M4.16663 4.16669L15.8333 15.8334"
              stroke="#3A3E41"
              stroke-width="1.75"
              stroke-linecap="round"
            ></path>
            <path
              d="M15.8334 4.16669L4.16671 15.8334"
              stroke="#3A3E41"
              stroke-width="1.75"
              stroke-linecap="round"
            ></path>
          </svg>
        </div>
      </div>
      <div class="css-1djguz4">
        <div class="css-yj1ay2">
          <div direction="vertical" size="40" class="css-ygt1wz"></div>
          <div class="css-bbe5fx2">회원님의 이메일은</div>
          <div direction="vertical" size="40" class="css-ygt1wz"></div>
          <div class="css-bbe5fx3">{{ userStore.userEmail }} 입니다.</div>
          <div direction="vertical" size="40" class="css-ygt1wz"></div>
          <button @click="moveLoginModal()" color="#FFFFFF" class="css-uscuon1">
            로그인하러 가기
          </button>
        </div>
      </div>
    </div>
  </div>
  <!----------------여기부터 비밀번호 찾기 모달창------------------------>
  <div class="css-1gpya5f" v-show="isFindPassword">
    <div class="css-4oebd2">
      <div class="css-1n1k95p">
        <div class="css-1a96vvi" @click="closeFindPasswordModal()">
          <svg
            width="20"
            height="20"
            viewBox="0 0 20 20"
            fill="none"
            xmlns="http://www.w3.org/2000/svg"
          >
            <path
              d="M9.16699 5L4.16699 10L9.16699 15"
              stroke="#3A3E41"
              stroke-width="1.75"
              stroke-linecap="round"
              stroke-linejoin="round"
            ></path>
            <path
              d="M5 10L15.8333 10"
              stroke="#3A3E41"
              stroke-width="1.75"
              stroke-linecap="round"
              stroke-linejoin="round"
            ></path>
          </svg>
          뒤로
        </div>
        <div class="css-myjkxi" @click="closeAllLoginModal()">
          <svg
            width="20"
            height="20"
            viewBox="0 0 20 20"
            fill="none"
            xmlns="http://www.w3.org/2000/svg"
          >
            <path
              d="M4.16663 4.16669L15.8333 15.8334"
              stroke="#3A3E41"
              stroke-width="1.75"
              stroke-linecap="round"
            ></path>
            <path
              d="M15.8334 4.16669L4.16671 15.8334"
              stroke="#3A3E41"
              stroke-width="1.75"
              stroke-linecap="round"
            ></path>
          </svg>
        </div>
      </div>
      <div class="css-1djguz4">
        <div class="css-yj1ay2">
          <div class="css-bbe5fx">비밀번호 찾기</div>
          <form id="findEmailForm"></form>
          <form id="loginForm">
            <div direction="vertical" size="40" class="css-ygt1wz"></div>
            <input
              v-model="email"
              placeholder="이메일 입력"
              type="string"
              autocapitalize="off"
              class="login-custom-input css-krmsb5"
            />
            <div direction="vertical" size="12" class="css-j86f64"></div>
            <input
              v-model="name"
              placeholder="이름 입력"
              type="nickName"
              autocapitalize="off"
              class="login-custom-input css-krmsb5"
            />
            <div direction="vertical" size="40" class="css-ygt1wz"></div>
          </form>
          <button
            @click="sendFindPassword()"
            @keyup.enter="sendFindPassword()"
            color="#FFFFFF"
            class="css-uscuon1"
          >
            확인
          </button>
        </div>
      </div>
    </div>
  </div>
  <div class="css-1gpya5f" v-show="isCheckPassword">
    <div class="css-4oebd2">
      <div class="css-1n1k95p">
        <div class="css-1a96vvi" @click="closeCheckPasswordModal()">
          <svg
            width="20"
            height="20"
            viewBox="0 0 20 20"
            fill="none"
            xmlns="http://www.w3.org/2000/svg"
          >
            <path
              d="M9.16699 5L4.16699 10L9.16699 15"
              stroke="#3A3E41"
              stroke-width="1.75"
              stroke-linecap="round"
              stroke-linejoin="round"
            ></path>
            <path
              d="M5 10L15.8333 10"
              stroke="#3A3E41"
              stroke-width="1.75"
              stroke-linecap="round"
              stroke-linejoin="round"
            ></path>
          </svg>
          뒤로
        </div>
        <div class="css-myjkxi" @click="closeAllLoginModal()">
          <svg
            width="20"
            height="20"
            viewBox="0 0 20 20"
            fill="none"
            xmlns="http://www.w3.org/2000/svg"
          >
            <path
              d="M4.16663 4.16669L15.8333 15.8334"
              stroke="#3A3E41"
              stroke-width="1.75"
              stroke-linecap="round"
            ></path>
            <path
              d="M15.8334 4.16669L4.16671 15.8334"
              stroke="#3A3E41"
              stroke-width="1.75"
              stroke-linecap="round"
            ></path>
          </svg>
        </div>
      </div>
      <div class="css-1djguz4">
        <div class="css-yj1ay2">
          <div direction="vertical" size="40" class="css-ygt1wz"></div>
          <div class="css-bbe5fx2">회원님의 임시 비밀번호가 입력하신</div>
          <div class="css-bbe5fx2">이메일 주소로 발송되었습니다.</div>
          <div direction="vertical" size="40" class="css-ygt1wz"></div>
          <div class="css-bbe5fx3">이메일을 확인하여 임시비밀번호로</div>
          <div class="css-bbe5fx3">로그인해주세요.</div>
          <div direction="vertical" size="40" class="css-ygt1wz"></div>
          <button @click="moveLoginModal()" color="#FFFFFF" class="css-uscuon1">
            로그인하러 가기
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { useUserStore } from "/src/stores/useUserStore";
import { mapStores } from "pinia";
import VueJwtDecode from "vue-jwt-decode";

export default {
  name: "HeaderComponent",
  props: {
    showBorder: {
      type: Boolean,
      default: true,
    },
    showMenu: {
      type: Boolean,
      default: true,
    },
    showLogo: {
      type: Boolean,
      default: true,
    },
  },
  data() {
    return {
      isLogin: false,
      isEmailLogin: false,
      isFindEmail: false,
      isCheckEmail: false,
      isFindPassword: false,
      isCheckPassword: false,
      isAdminLogin: false,
      isDropdownOpen: false,
      email: "",
      password: "",
      profileImage: "",
      nickName: "",
      name: "",
    };
  },
  computed: {
    ...mapStores(useUserStore),

    isAuthenticated() {
      const store = useUserStore();
      return store.isAuthenticated;
    },
    decodedToken() {
      const store = useUserStore();
      return store.decodedToken;
    },
    isLoggedIn() {
      return !!localStorage.getItem("token");
    },
    userProfileImage() {
      const store = useUserStore();
      if (store.user && store.user.profileImage) {
        return store.user.profileImage;
      }
      return null;
    },
  },
  mounted() {
    const token = localStorage.getItem("token");

    if (token) {
      const role = VueJwtDecode.decode(token).ROLE;
      if (role === "ROLE_ADMIN") {
        this.isAdminLogin = true;
      } else {
        this.isAdminLogin = false;
      }
    }
  },
  methods: {
    loginKakao() {
      // window.location.href = "http://localhost:8080/oauth2/authorization/kakao";
      window.location.href =
        "http://192.168.0.61/api/oauth2/authorization/kakao";
    },
    openLoginModal() {
      this.isLogin = true;
    },
    closeLoginModal() {
      this.isLogin = false;
    },
    openEmailLoginModal() {
      this.isEmailLogin = true;
      this.isLogin = false;
    },
    openFindEmailModal() {
      this.isFindEmail = true;
      this.isEmailLogin = false;
    },
    openFindPasswordModal() {
      this.isFindPassword = true;
      this.isEmailLogin = false;
    },
    closeEmailLoginModal() {
      this.isLogin = true;
      this.isEmailLogin = false;
    },
    closeFindEmailModal() {
      this.isEmailLogin = true;
      this.isFindEmail = false;
    },
    closeCheckEmailModal() {
      this.isEmailLogin = true;
      this.isCheckEmail = false;
      (this.name = ""), (this.nickName = "");
    },
    closeCheckPasswordModal() {
      this.isEmailLogin = true;
      this.isCheckPassword = false;
      (this.email = ""), (this.name = "");
    },
    closeFindPasswordModal() {
      this.isEmailLogin = true;
      this.isFindPassword = false;
      (this.email = ""), (this.name = "");
    },
    closeAllLoginModal() {
      this.isLogin = false;
      this.isEmailLogin = false;
      this.isFindEmail = false;
      this.isCheckEmail = false;
      this.isFindPassword = false;
      this.isCheckPassword = false;
      (this.name = ""), (this.nickName = ""), (this.email = ""), (this.password = "");
    },
    moveLoginModal() {
      this.isEmailLogin = true;
      this.isCheckEmail = false;
      this.isCheckPassword = false;
      (this.name = ""), (this.nickName = ""), (this.email = "");
    },
    selectSignUp() {
      window.location.href = "/select/signup";
    },
    logout() {
      window.localStorage.removeItem("token");
      const store = useUserStore();
      store.isAuthenticated = false;
      store.decodedToken = {};
      this.isDropdownOpen = false;
      this.$router.push("/");
      window.location.reload();
      this.email = "";
      this.password = "";
    },
    goMyProfile() {
      window.location.href = "/profile";
    },
    goMyPage() {
      window.location.href = "/mypage";
    },
    goAdminPage() {
      window.location.href = "/admin/login";
    },
    decodeToken(token) {
      const base64Url = token.split(".")[1];
      const base64 = base64Url.replace(/-/g, "+").replace(/_/g, "/");
      const jsonPayload = decodeURIComponent(
        atob(base64)
          .split("")
          .map((c) => "%" + ("00" + c.charCodeAt(0).toString(16)).slice(-2))
          .join("")
      );
      return JSON.parse(jsonPayload);
    },
    async onLoginFormSubmit() {
      try {
        await this.userStore.login(this.email, this.password);
        if (this.userStore.isAuthenticated === true) {
          this.closeAllLoginModal();
          window.location.href = "/";
          // this.$router.push("/");
          this.closeLoginModal();
        }
      } catch (error) {
        console.error("Login failed:", error);
      }
    },
    async sendFindEmail() {
      try {
        await this.userStore.findEmail(this.name, this.nickName);
        if (this.userStore.isFindEmailSuccess === true) {
          this.isCheckEmail = true;
          this.isFindEmail = false;
        }
      } catch (error) {
        console.error("Find Email failed:", error);
      }
    },
    async sendFindPassword() {
      try {
        await this.userStore.findPassword(this.email, this.name);
        if (this.userStore.isFindPasswordSuccess === true) {
          this.isCheckPassword = true;
          this.isFindPassword = false;
        }
      } catch (error) {
        console.error("Find Password failed:", error);
      }
    },
    toggleDropdownMenu() {
      console.log("Dropdown toggled");
      this.isDropdownOpen = !this.isDropdownOpen;
    },
  },
  created() {
    const token = window.localStorage.getItem("token");
    if (token) {
      const decoded = this.decodeToken(token);
      const store = useUserStore();
      store.setDecodedToken(decoded);
      store.isAuthenticated = true;
    }
    if (this.isLoggedIn) {
      this.userStore.getUserInfo();
    }
  },
};
</script>

<style scoped>
@font-face {
  font-display: swap;
  font-family: "SpoqaHanSansNeo";
  font-style: normal;
  font-weight: 400;
  src: url("https://static.spartacodingclub.kr/static/fonts/SpoqaHanSansNeo/SpoqaHanSansNeo-Regular.woff")
      format("woff"),
    url("https://static.spartacodingclub.kr/static/fonts/SpoqaHanSansNeo/SpoqaHanSansNeo-Regular.otf")
      format("otf");
}

@font-face {
  font-display: swap;
  font-family: "GmarketSans";
  font-weight: 300;
  font-style: normal;
  src: url("https://static.spartacodingclub.kr/static/fonts/GmarketSans/GmarketSansLight.woff2")
      format("woff2"),
    url("https://static.spartacodingclub.kr/static/fonts/GmarketSans/GmarketSansLight.woff")
      format("woff");
}

@font-face {
  font-display: swap;
  font-family: "GmarketSans";
  font-weight: 400;
  font-style: normal;
  src: url("https://static.spartacodingclub.kr/static/fonts/GmarketSans/GmarketSansMedium.woff2")
      format("woff2"),
    url("https://static.spartacodingclub.kr/static/fonts/GmarketSans/GmarketSansMedium.woff")
      format("woff");
}

@font-face {
  font-display: swap;
  font-family: "GmarketSans";
  font-weight: 700;
  font-style: normal;
  src: url("https://static.spartacodingclub.kr/static/fonts/GmarketSans/GmarketSansBold.woff2")
      format("woff2"),
    url("https://static.spartacodingclub.kr/static/fonts/GmarketSans/GmarketSansBold.woff")
      format("woff");
}

@font-face {
  font-display: swap;
  font-family: "Pretendard";
  font-weight: 100;
  font-style: normal;
  src: local(Pretendard Thin),
    url("https://static.spartacodingclub.kr/static/fonts/Pretendard/Pretendard-Thin.subset.woff2")
      format("woff2"),
    url("https://static.spartacodingclub.kr/static/fonts/Pretendard/Pretendard-Thin.subset.woff")
      format("woff"),
    url("https://static.spartacodingclub.kr/static/fonts/Pretendard/Pretendard-Thin.otf");
}

@font-face {
  font-display: swap;
  font-family: "Pretendard";
  font-weight: 200;
  font-style: normal;
  src: local(Pretendard ExtraLight),
    url("https://static.spartacodingclub.kr/static/fonts/Pretendard/Pretendard-ExtraLight.subset.woff2")
      format("woff2"),
    url("https://static.spartacodingclub.kr/static/fonts/Pretendard/Pretendard-ExtraLight.subset.woff")
      format("woff"),
    url("https://static.spartacodingclub.kr/static/fonts/Pretendard/Pretendard-ExtraLight.otf");
}

@font-face {
  font-display: swap;
  font-family: "Pretendard";
  font-weight: 300;
  font-style: normal;
  src: local(Pretendard Light),
    url("https://static.spartacodingclub.kr/static/fonts/Pretendard/Pretendard-Light.subset.woff2")
      format("woff2"),
    url("https://static.spartacodingclub.kr/static/fonts/Pretendard/Pretendard-Light.subset.woff")
      format("woff"),
    url("https://static.spartacodingclub.kr/static/fonts/Pretendard/Pretendard-Light.otf");
}

@font-face {
  font-display: swap;
  font-family: "Pretendard";
  font-weight: 400;
  font-style: normal;
  src: local(Pretendard Regular),
    url("https://static.spartacodingclub.kr/static/fonts/Pretendard/Pretendard-Regular.subset.woff2")
      format("woff2"),
    url("https://static.spartacodingclub.kr/static/fonts/Pretendard/Pretendard-Regular.subset.woff")
      format("woff"),
    url("https://static.spartacodingclub.kr/static/fonts/Pretendard/Pretendard-Regular.otf");
}

@font-face {
  font-display: swap;
  font-family: "Pretendard";
  font-weight: 500;
  font-style: normal;
  src: local(Pretendard Medium),
    url("https://static.spartacodingclub.kr/static/fonts/Pretendard/Pretendard-Medium.subset.woff2")
      format("woff2"),
    url("https://static.spartacodingclub.kr/static/fonts/Pretendard/Pretendard-Medium.subset.woff")
      format("woff"),
    url("https://static.spartacodingclub.kr/static/fonts/Pretendard/Pretendard-Medium.otf");
}

@font-face {
  font-display: swap;
  font-family: "Pretendard";
  font-weight: 600;
  font-style: normal;
  src: local(Pretendard SemiBold),
    url("https://static.spartacodingclub.kr/static/fonts/Pretendard/Pretendard-SemiBold.subset.woff2")
      format("woff2"),
    url("https://static.spartacodingclub.kr/static/fonts/Pretendard/Pretendard-SemiBold.subset.woff")
      format("woff"),
    url("https://static.spartacodingclub.kr/static/fonts/Pretendard/Pretendard-SemiBold.otf");
}

@font-face {
  font-display: swap;
  font-family: "Pretendard";
  font-weight: 700;
  font-style: normal;
  src: local(Pretendard Bold),
    url("https://static.spartacodingclub.kr/static/fonts/Pretendard/Pretendard-Bold.subset.woff2")
      format("woff2"),
    url("https://static.spartacodingclub.kr/static/fonts/Pretendard/Pretendard-Bold.subset.woff")
      format("woff"),
    url("https://static.spartacodingclub.kr/static/fonts/Pretendard/Pretendard-Bold.otf");
}

@font-face {
  font-display: swap;
  font-family: "Pretendard";
  font-weight: 800;
  font-style: normal;
  src: url("https://static.spartacodingclub.kr/static/fonts/Pretendard/Pretendard-ExtraBold.subset.woff2")
      format("woff2"),
    url("https://static.spartacodingclub.kr/static/fonts/Pretendard/Pretendard-ExtraBold.subset.woff")
      format("woff"),
    url("https://static.spartacodingclub.kr/static/fonts/Pretendard/Pretendard-ExtraBold.otf");
}

@font-face {
  font-display: swap;
  font-family: "Pretendard";
  font-weight: 900;
  font-style: normal;
  src: url("https://static.spartacodingclub.kr/static/fonts/Pretendard/Pretendard-Black.subset.woff2")
      format("woff2"),
    url("https://static.spartacodingclub.kr/static/fonts/Pretendard/Pretendard-Black.subset.woff")
      format("woff"),
    url("https://static.spartacodingclub.kr/static/fonts/Pretendard/Pretendard-Black.otf");
}

@font-face {
  font-display: swap;
  font-family: "SpoqaHanSans";
  font-weight: 300;
  font-style: normal;
  src: url("https://static.spartacodingclub.kr/static/fonts/SpoqaHanSans/SpoqaHanSans-Light.woff")
    format("woff");
}

@font-face {
  font-display: swap;
  font-family: "SpoqaHanSans";
  font-weight: 400;
  font-style: normal;
  src: url("https://static.spartacodingclub.kr/static/fonts/SpoqaHanSans/SpoqaHanSans-Regular.woff")
    format("woff");
}

@font-face {
  font-display: swap;
  font-family: "SpoqaHanSans";
  font-weight: 700;
  font-style: normal;
  src: url("https://static.spartacodingclub.kr/static/fonts/SpoqaHanSans/SpoqaHanSans-Bold.woff")
    format("woff");
}

@font-face {
  font-display: swap;
  font-family: "Cafe24Ohsquare";
  font-weight: 500;
  font-style: normal;
  src: url("https://static.spartacodingclub.kr/static/fonts/Cafe24Ohsquare/Cafe24Ohsquare.woff2")
      format("woff2"),
    url("https://static.spartacodingclub.kr/static/fonts/Cafe24Ohsquare/Cafe24Ohsquare.woff")
      format("woff"),
    url("https://static.spartacodingclub.kr/static/fonts/Cafe24Ohsquare/Cafe24Ohsquare.ttf");
}

@font-face {
  font-family: "NanumHandWritingDaughter";
  font-style: normal;
  font-weight: 400;
  src: url("https://static.spartacodingclub.kr/static/fonts/NanumHandWritingDaughter.subset.woff2")
    format("woff2");
}

@font-face {
  font-family: "ThefaceshopInklipquid";
  font-style: normal;
  font-weight: 400;
  src: url("https://static.spartacodingclub.kr/static/fonts/ThefaceshopInklipquid/ThefaceshopInklipquid.woff")
      format("woff"),
    url("https://static.spartacodingclub.kr/static/fonts/ThefaceshopInklipquid/ThefaceshopInklipquid.ttf");
}

@font-face {
  font-family: "SbAggroBold";
  font-weight: 700;
  font-style: normal;
  src: url("https://static.spartacodingclub.kr/static/fonts/SbAggro/SB_aggro_bold.woff2")
      format("woff2"),
    url("https://static.spartacodingclub.kr/static/fonts/SbAggro/SB_aggro_bold.woff")
      format("woff"),
    url("https://static.spartacodingclub.kr/static/fonts/SbAggro/SB_aggro_bold.ttf");
}

@font-face {
  font-family: "DungGeunMo";
  font-style: normal;
  font-weight: 400;
  src: url("https://static.spartacodingclub.kr/static/fonts/DungGeunMo/DungGeunMo.woff")
      format("woff"),
    url("https://static.spartacodingclub.kr/static/fonts/DungGeunMo/DungGeunMo.ttf");
}

@font-face {
  font-family: "Tmoney RoundWind";
  font-style: normal;
  font-weight: 400;
  src: url("https://static.spartacodingclub.kr/static/fonts/TmoneyRoundWindExtraBold.woff2")
      format("woff2"),
    url("https://static.spartacodingclub.kr/static/fonts/TmoneyRoundWindExtraBold.ttf");
}

@font-face {
  font-family: "Cafe24Surround";
  font-style: normal;
  font-weight: 400;
  src: url("https://static.spartacodingclub.kr/static/fonts/Cafe24Ssurround-v2.0.woff2")
      format("woff2"),
    url("https://static.spartacodingclub.kr/static/fonts/Cafe24Ssurround-v2.0.woff");
}

@font-face {
  font-family: "EBSHunminjeongeumSBA";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_one@1.0/EBSHunminjeongeum.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}

@font-face {
  font-family: "Dokrip";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_twelve@1.1/Dokrip.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}

@font-face {
  font-family: "YoonDokrip";
  font-style: normal;
  font-weight: 500;
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_twelve@1.1/Dokrip.woff")
      format("woff"),
    url("https://static.spartacodingclub.kr/static/fonts/YoonDokrip.woff")
      format("woff"),
    url("https://static.spartacodingclub.kr/static/fonts/YoonDokrip.otf")
      format("otf");
}

.css-huvhzc {
  height: auto;
  /* 이미지의 높이를 자동 조정하여 헤더와 잘 맞게 배치 */
  max-height: 100%;
  /* 이미지가 너무 크게 확대되지 않도록 제한 */
  max-width: 100px;
  /* 이미지의 최대 너비를 설정하여 헤더와 너무 커지지 않도록 제한 */
}

.css-13fd4ke {
  padding: 12px 0;
  cursor: pointer;
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  -webkit-align-items: center;
  -webkit-box-align: center;
  -ms-flex-align: center;
  align-items: center;
  -webkit-box-pack: justify;
  -webkit-justify-content: space-between;
  justify-content: space-between;
  -webkit-tap-highlight-color: rgba(255, 255, 255, 0);
  width: 100%;
  padding-left: 10px;
}

.css-wkr2a ::before,
.css-wkr2a ::after {
  box-sizing: inherit;
}

.css-wkr2a {
  z-index: 20;
  display: flex;
  align-items: center;
  justify-content: center;
  /* 헤더 내의 요소들을 수평 중앙으로 정렬합니다. */
  transition: all 0.4s ease 0s;
  background-color: white;
  height: 70px;
  width: 100%;
  position: fixed;
  top: 0;
  /* 헤더를 화면 상단에 고정시킵니다. */
}

.css-wkr2a * {
  word-break: normal;
  white-space: nowrap;
  box-sizing: border-box;
}

.css-we70d5 {
  width: 100%;
  max-width: 1100px;
  display: flex;
  justify-content: space-between;
  /* 요소들을 양쪽 끝에 배치 */
  align-items: center;
  /* 세로 중앙 정렬 */
  margin: 0 auto;
  /* 가운데 정렬을 위해 margin을 0으로 설정 */
  padding-top: 10px;
}

/* 상단 검색 창  */
.css-1butcu2 {
  position: relative;
  display: flex;
  gap: 50px;
  -webkit-box-align: center;
  align-items: center;
}

.ContentSearch_searchWrapper__3xeqG2 {
  position: relative;
  display: flex;
  justify-content: center;
  align-items: center;
  width: 90%;
  max-width: 50rem;
  height: 7.6rem;
  padding: 0;
  /* Adjusted for direct child input styling */
  border: 0.6rem solid var(rgb(84, 29, 112));
  /* Light Blue outer border */
  border-radius: 8.4rem;
  background: transparent;
  /* Ensuring background doesn't interfere with outer border */
}

.ContentSearch_searchInput__dVET92 {
  margin-right: 1rem;
  display: flex;
  align-items: center;
  width: calc(100% - 1rem);
  /* Adjusting width to fit within the wrapper */
  height: 4rem;
  /* Adjusting height to fit within the wrapper */
  padding: 1.7rem 2rem;
  gap: 1.5rem;
  font-size: 1.5rem;
  border: 0.2rem solid var(--border-inner);
  /* Blue inner border */
  border-radius: 8.5rem;
  background: white;
  /* White inner background */
  color: red;
  /* Text color */
  box-shadow: 2px 4px 8px rgba(0, 0, 0, 0.1);
  /* 그림자 색상과 크기 설정 */
}

.css-1llgdoe {
  font-family: Pretendard;
  font-style: normal;
  font-weight: 600;
  font-size: 16px;
  line-height: 26px;
  padding: 9px 10px 8px;
  color: rgb(20, 22, 23);
  cursor: pointer;
  display: flex;
  flex-direction: row;
  gap: 6px;
  -webkit-box-align: center;
  align-items: center;
}

.css-1butcu {
  position: relative;
  display: flex;
  gap: 16px;
  -webkit-box-align: center;
  align-items: center;
}

/* 로그인 버튼 */
.css-login-button {
  margin-right: 10px;
  background-color: white;
  /* 초록색 배경색 */
  color: black;
  /* 글자색 */
  font-size: 16px;
  font-weight: 620;
  width: 100px;
  border: 0.2rem solid var(--border-inner);
  /* Blue inner border */
  border-radius: 8.5rem;
  box-shadow: 2px 4px 8px rgba(0, 0, 0, 0.1);
}

/* 회원가입 버튼 */
.css-signup-button {
  margin-right: 10px;
  background-color: rgb(84, 29, 112);
  /* 파란색 배경색 */
  color: #fff;
  /* 글자색 */
  font-size: 16px;
  font-weight: 620;
  width: 100px;
  border: 0.2rem solid var(--border-inner);
  /* Blue inner border */
  border-radius: 8.5rem;
  box-shadow: 2px 8px 10px rgba(0, 0, 0, 0.1);
}

/* 호버 효과 */
.css-button:hover {
  transform: translateY(-2px);
  box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
}

.css-26c0za {
  cursor: pointer;
  position: relative;
  display: flex;
  -webkit-box-align: center;
  align-items: center;
  -webkit-box-pack: center;
  justify-content: center;
}

.css-1fgr4mj-active {
  opacity: 1;
  visibility: visible;
}

.css-1fgr4mj {
  display: block;
  padding: 25px 0 0 0;
  border: #d7e0e6 solid 1px;
  border-radius: 10px;
  position: absolute;
  top: calc(40px + 0px);
  right: 0;
  background-color: white;
  z-index: 100;
  width: 230px;
  box-shadow: 0px 8px 24px rgba(95, 102, 107, 0.12);
  font-family: "Pretendard";
  overflow: scroll;
  scrollbar-width: none;
  /* 드롭다운을 완전히 오른쪽으로 이동하지 않게 하기 위한 조정 */
}

.css-13t7veh {
  display: flex;
  padding: 16px;
  -webkit-box-align: center;
  align-items: center;
  gap: 12px;
  align-self: stretch;
  border-radius: 8px;
  border: 1px solid rgb(228, 235, 240);
  background: rgb(255, 255, 255);
  box-shadow: rgba(95, 102, 107, 0.12) 0px 8px 24px 0px;
  margin: 0px 16px;
}

.Icon_container__KMOfZ2 + .ContentSearch_searchInput__dVET92 {
  flex-grow: 1;
  color: rgb(82, 29, 112);
  /* 아이콘 색상 변경 */
  box-shadow: 2px 2px 4px rgba(0, 0, 0, 0.1);
  /* 그림자 색상과 크기 설정 */
  margin-right: 1.5rem;
  /* Adding space between icon and input field */
}

.Icon_container__KMOfZ2 svg {
  width: 4rem;
  /* 아이콘의 너비를 3.2rem으로 조정합니다. */
  height: 4rem;
  /* 아이콘의 높이를 3.2rem으로 조정합니다. */
  transition: all 0.2s ease-in-out;
}

.css-un3w91 {
  cursor: pointer;
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

.css-16regn8 {
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  -webkit-align-items: center;
  -webkit-box-align: center;
  -ms-flex-align: center;
  align-items: center;
  gap: 10px;
  font-family: Pretendard, -apple-system, “system-ui”, "Malgun Gothic",
    "맑은 고딕", sans-serif;
  font-family: Pretendard;
  font-style: normal;
  font-weight: 600;
  font-size: 16px;
  line-height: 22px;
  color: #141617;
  color: #141617;
}

.css-17gywxx {
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  -webkit-align-items: center;
  -webkit-box-align: center;
  -ms-flex-align: center;
  align-items: center;
  font-family: Pretendard;
  font-style: normal;
  font-weight: 600;
  font-size: 16px;
  line-height: 22px;
  color: #141617;
  color: #141617;
  color: #3a3e41;
}

.css-1fgr4mj::-webkit-scrollbar {
  display: none;
}

.css-1fgr4mj::after {
  border-color: #ffff transparent;
  border-style: solid;
  border-width: 0 6px 8px 6.5px;
  content: "";
  display: block;
  right: 5%;
  position: absolute;
  top: -7px;
  width: 0;
  z-index: 1;
}

.css-1fgr4mj::before {
  border-color: #d7e0e6 transparent;
  border-style: solid;
  border-width: 0 6px 8px 6.5px;
  content: "";
  display: block;
  right: 5%;
  position: absolute;
  top: -8px;
  width: 0;
  z-index: 0;
}

.css-1bkawov {
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  -webkit-flex-direction: column;
  -ms-flex-direction: column;
  flex-direction: column;
  height: -webkit-fit-content;
  height: -moz-fit-content;
  height: fit-content;
  padding-bottom: 12px;
  gap: 16px;
}

.css-gfzvs2 {
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  -webkit-flex-direction: column;
  -ms-flex-direction: column;
  flex-direction: column;
  font-family: Pretendard, -apple-system, “system-ui”, "Malgun Gothic",
    "맑은 고딕", sans-serif;
  font-weight: 700;
  font-size: 18px;
  line-height: 140%;
  color: #141617;
  text-align: center;
  gap: 24px;
}

.css-gfzvs2::-moz-selection {
  background: rgba(0, 0, 0, 0);
}

.css-gfzvs2::selection {
  background: rgba(0, 0, 0, 0);
}

.css-ss0hg9 {
  width: 100%;
  margin: 0 auto;
}

.css-1o9qtii {
  width: 100%;
  height: 1px;
  background: #e4ebf0;
  margin: 0 0;
}

.css-1s9cv9y {
  padding: 16px;
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  -webkit-flex-direction: column;
  -ms-flex-direction: column;
  flex-direction: column;
}

.css-zbmmrt {
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  padding: 16px;
  -webkit-box-pack: justify;
  -webkit-justify-content: space-between;
  justify-content: space-between;
  -webkit-align-items: center;
  -webkit-box-align: center;
  -ms-flex-align: center;
  align-items: center;
  -webkit-flex: 1 0 0;
  -ms-flex: 1 0 0;
  flex: 1 0 0;
  border-radius: 8px;
  background: #e7f9e7;
  margin-top: 16px;
  cursor: pointer;
}

.css-wwyx62 {
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  width: 201.5px;
  -webkit-align-items: center;
  -webkit-box-align: center;
  -ms-flex-align: center;
  align-items: center;
  gap: 12px;
}

.css-1hq5jbp {
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  width: 36px;
  height: 36px;
  padding: 5.76px 1.474px 7.103px 1.875px;
  -webkit-box-pack: center;
  -ms-flex-pack: center;
  -webkit-justify-content: center;
  justify-content: center;
  -webkit-align-items: center;
  -webkit-box-align: center;
  -ms-flex-align: center;
  align-items: center;
  -webkit-flex-shrink: 0;
  -ms-flex-negative: 0;
  flex-shrink: 0;
}

.css-1bku0gi {
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  -webkit-flex-direction: column;
  -ms-flex-direction: column;
  flex-direction: column;
  -webkit-align-items: flex-start;
  -webkit-box-align: flex-start;
  -ms-flex-align: flex-start;
  align-items: flex-start;
}

.css-1smjha1 {
  font-family: Pretendard;
  font-style: normal;
  font-weight: 400;
  font-size: 11px;
  line-height: 16px;
  color: #141617;
  color: #81898f;
}

.css-3aha2l {
  font-family: Pretendard;
  font-style: normal;
  font-weight: 600;
  font-size: 16px;
  line-height: 22px;
  color: #141617;
  color: #3a3e41;
}

.css-ik70s9 {
  padding: 8px 0;
  cursor: pointer;
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  -webkit-align-items: center;
  -webkit-box-align: center;
  -ms-flex-align: center;
  align-items: center;
  -webkit-box-pack: justify;
  -webkit-justify-content: space-between;
  justify-content: space-between;
  -webkit-tap-highlight-color: rgba(255, 255, 255, 0);
  width: 100%;
  padding-left: 10px;
}

.css-28a73i {
  color: #5f666b;
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  -webkit-align-items: center;
  -webkit-box-align: center;
  -ms-flex-align: center;
  align-items: center;
  gap: 8px;
  font-family: Pretendard, -apple-system, “system-ui”, "Malgun Gothic",
    "맑은 고딕", sans-serif;
  font-family: Pretendard;
  font-style: normal;
  font-weight: 600;
  font-size: 16px;
  line-height: 22px;
  color: #141617;
  color: #141617;
}

.css-4l7ba3 {
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  -webkit-align-items: center;
  -webkit-box-align: center;
  -ms-flex-align: center;
  align-items: center;
  font-family: Pretendard;
  font-style: normal;
  font-weight: 600;
  font-size: 16px;
  line-height: 22px;
  color: #141617;
  color: #141617;
  font-size: 14px;
  font-weight: 500;
  color: #5f666b;
}

@media (min-width: 320px) and (max-width: 819px) {
  .css-gfzvs2 {
    font-size: 16px;
    padding: 0;
  }
}

@media (max-width: 820px) {
  .css-4l7ba3 {
    font-family: Pretendard;
    font-style: normal;
    font-weight: 600;
    font-size: 14px;
    line-height: 22px;
    color: #141617;
    font-size: 13px;
    font-weight: 500;
    color: #5f666b;
  }

  .css-ik70s9:hover {
    color: #e8344e;
  }

  .css-3aha2l {
    font-family: Pretendard;
    font-style: normal;
    font-weight: 600;
    font-size: 14px;
    line-height: 22px;
    color: #141617;
    color: #3a3e41;
  }

  .css-17gywxx {
    font-family: Pretendard;
    font-style: normal;
    font-weight: 600;
    font-size: 14px;
    line-height: 22px;
    color: #141617;
    color: #3a3e41;
  }

  .css-13fd4ke:hover {
    color: #e8344e;
  }

  .css-ss0hg9 {
    width: 250px;
  }

  .css-1s9cv9y {
    padding: 16px 0 0 0;
    gap: 0;
  }

  .css-zbmmrt {
    padding: 12px;
    margin: 16px 0;
  }
}

@media (min-width: 1200px) {
  .css-28a73i {
    height: 24px;
  }

  .css-16regn8 {
    height: 24px;
  }
}

/*------------------로그인 모달--------------------*/
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

* {
  line-height: 1.5;
  box-sizing: border-box;
  letter-spacing: normal;
}

* {
  margin: 0;
  line-height: 1.5;
}

.css-1gpya5f {
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
  -webkit-box-align: center;
  align-items: center;
  background: rgba(20, 22, 23, 0.4);
  scrollbar-width: none;
}

.css-4oebd2 {
  width: 520px;
  height: 580px;
  padding: 0px 20px 40px;
  background: rgb(255, 255, 255);
  border-radius: 12px;
  position: relative;
  box-sizing: border-box;
  display: flex;
  flex-direction: column;
}

.css-1n1k95p {
  width: 100%;
  height: 48px;
  position: relative;
  flex-shrink: 0;
}

.css-myjkxi {
  position: absolute;
  top: 18px;
  right: 0px;
  font-family: Pretendard;
  font-style: normal;
  font-weight: 600;
  font-size: 16px;
  line-height: 26px;
  color: rgb(58, 62, 65);
  display: flex;
  -webkit-box-align: center;
  align-items: center;
  gap: 4px;
  cursor: pointer;
}

.css-1xpr18p {
  width: 336px;
  overflow: hidden;
  margin: auto;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  flex-grow: 1;
}

.css-gxukgu {
  font-family: Pretendard;
  font-style: normal;
  font-weight: 600;
  font-size: 24px;
  line-height: 34px;
  color: rgb(20, 22, 23);
}

.css-gxukgu span {
  font-family: Pretendard;
  font-style: normal;
  font-weight: 400;
  font-size: 16px;
  line-height: 26px;
  color: rgb(20, 22, 23);
  margin-bottom: 2px;
}

.css-j86f64 {
  width: 100%;
  height: 12px;
}

.css-1hal8c {
  font-family: Pretendard;
  font-style: normal;
  font-weight: 400;
  font-size: 16px;
  line-height: 26px;
  color: rgb(95, 102, 107);
  white-space: pre-line;
}

.css-ygt1wz {
  width: 100%;
  height: 40px;
}

button {
  background: unset;
  border: unset;
  padding: unset;
}

a,
button {
  text-decoration: none;
  cursor: pointer;
}

.css-18wq8ro {
  border: none;
  cursor: pointer;
  width: 99.5%;
  height: 56px;
  background: rgb(254, 229, 0);
  font-family: Pretendard;
  font-style: normal;
  font-weight: 600;
  font-size: 16px;
  line-height: 26px;
  color: rgb(20, 22, 23);
  border-radius: 60px;
  display: flex;
  -webkit-box-pack: center;
  justify-content: center;
  -webkit-box-align: center;
  align-items: center;
  gap: 8px;
}

.css-10vhklm {
  width: 100%;
  height: 16px;
}

.css-fqtlj {
  border: none;
  cursor: pointer;
  width: 99.5%;
  height: 56px;
  background: rgb(238, 243, 246);
  font-family: Pretendard;
  font-style: normal;
  font-weight: 600;
  font-size: 16px;
  line-height: 26px;
  color: rgb(20, 22, 23);
  border-radius: 60px;
  display: flex;
  -webkit-box-pack: center;
  justify-content: center;
  -webkit-box-align: center;
  align-items: center;
  gap: 8px;
}

.css-h23ofx {
  width: 100%;
  height: 32px;
}

.css-1cap2q {
  font-family: Pretendard;
  font-style: normal;
  font-weight: 500;
  font-size: 14px;
  line-height: 22px;
  color: rgb(129, 137, 143);
  cursor: pointer;
  width: 76px;
  text-align: center;
  white-space: nowrap;
}

.css-1sfln3b {
  width: 328px;
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-left: 17px;
}

/* ----------여기까지 로그인 첫 클릭 시 모달 창---------- */
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

* {
  line-height: 1.5;
  box-sizing: border-box;
  letter-spacing: normal;
}

* {
  margin: 0;
  line-height: 1.5;
}

.css-1gpya5f {
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
  -webkit-box-align: center;
  align-items: center;
  background: rgba(20, 22, 23, 0.4);
  scrollbar-width: none;
}

.css-4oebd2 {
  width: 520px;
  height: 580px;
  padding: 0px 20px 40px;
  background: rgb(255, 255, 255);
  border-radius: 12px;
  position: relative;
  box-sizing: border-box;
  display: flex;
  flex-direction: column;
}

.css-1n1k95p {
  width: 100%;
  height: 48px;
  position: relative;
  flex-shrink: 0;
}

.css-myjkxi {
  position: absolute;
  top: 18px;
  right: 0px;
  font-family: Pretendard;
  font-style: normal;
  font-weight: 600;
  font-size: 16px;
  line-height: 26px;
  color: rgb(58, 62, 65);
  display: flex;
  -webkit-box-align: center;
  align-items: center;
  gap: 4px;
  cursor: pointer;
}

.css-1xpr18p {
  width: 328px;
  overflow: hidden;
  margin: auto;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  flex-grow: 1;
}

.css-kcgo1q {
  display: flex;
  flex-direction: column;
  -webkit-box-align: center;
  align-items: center;
  text-align: center;
  width: 100%;
  padding-top: 60px;
  box-sizing: border-box;
}

.css-gxukgu {
  font-family: Pretendard;
  font-style: normal;
  font-weight: 600;
  font-size: 24px;
  line-height: 34px;
  color: rgb(20, 22, 23);
}

.css-gxukgu span {
  font-family: Pretendard;
  font-style: normal;
  font-weight: 400;
  font-size: 16px;
  line-height: 26px;
  color: rgb(20, 22, 23);
  margin-bottom: 2px;
}

.css-j86f64 {
  width: 100%;
  height: 12px;
}

.css-1hal8c {
  font-family: Pretendard;
  font-style: normal;
  font-weight: 400;
  font-size: 16px;
  line-height: 26px;
  color: rgb(95, 102, 107);
  white-space: pre-line;
}

.css-ygt1wz {
  width: 100%;
  height: 40px;
}

button {
  background: unset;
  border: unset;
  padding: unset;
}

a,
button {
  text-decoration: none;
  cursor: pointer;
}

.css-18wq8ro {
  border: none;
  cursor: pointer;
  width: 99.5%;
  height: 56px;
  background: rgb(254, 229, 0);
  font-family: Pretendard;
  font-style: normal;
  font-weight: 600;
  font-size: 16px;
  line-height: 26px;
  color: rgb(20, 22, 23);
  border-radius: 60px;
  display: flex;
  -webkit-box-pack: center;
  justify-content: center;
  -webkit-box-align: center;
  align-items: center;
  gap: 8px;
}

.css-10vhklm {
  width: 100%;
  height: 16px;
}

.css-fqtlj {
  border: none;
  cursor: pointer;
  width: 99.5%;
  height: 56px;
  background: rgb(238, 243, 246);
  font-family: Pretendard;
  font-style: normal;
  font-weight: 600;
  font-size: 16px;
  line-height: 26px;
  color: rgb(20, 22, 23);
  border-radius: 60px;
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 8px;
}

.css-h23ofx {
  width: 100%;
  height: 32px;
}

.css-1cap2q {
  font-family: Pretendard;
  font-style: normal;
  font-weight: 500;
  font-size: 14px;
  line-height: 22px;
  /* color: rgb(129, 137, 143); */
  color: #81898f;
  cursor: pointer;
  width: 76px;
  text-align: center;
  white-space: nowrap;
}

.css-1sfln3b {
  width: 296px;
  display: flex;
  flex-direction: column;
  align-items: center;
}

/* ----------여기까지 로그인 첫 클릭 시 모달 창---------- */

.css-1a96vvi {
  position: absolute;
  top: 18px;
  left: 0px;
  font-family: Pretendard;
  font-style: normal;
  font-weight: 600;
  font-size: 16px;
  line-height: 26px;
  color: rgb(58, 62, 65);
  display: flex;
  -webkit-box-align: center;
  align-items: center;
  gap: 4px;
  cursor: pointer;
}

.css-1djguz4 {
  width: 328px;
  overflow: hidden;
  margin: auto;
  display: flex;
  flex-direction: column;
  -webkit-box-pack: justify;
  justify-content: space-between;
  -webkit-box-flex: 1;
  flex-grow: 1;
}

.css-yj1ay2 {
  display: flex;
  flex-direction: column;
  -webkit-box-align: center;
  align-items: center;
  text-align: center;
  width: 100%;
  padding-top: 70px;
  box-sizing: border-box;
}

.css-bbe5fx {
  font-family: Pretendard;
  font-style: normal;
  font-weight: 600;
  font-size: 20px;
  line-height: 26px;
  color: rgb(20, 22, 23);
}

.css-bbe5fx2 {
  font-family: Pretendard;
  font-style: normal;
  font-weight: 600;
  font-size: 20px;
  line-height: 26px;
  color: rgb(20, 22, 23);
  margin-bottom: 15px;
}

.css-bbe5fx3 {
  font-family: Pretendard;
  font-style: normal;
  font-weight: 600;
  font-size: 20px;
  line-height: 26px;
  color: rgb(20, 22, 23);
  margin-bottom: 15px;
}

input {
  border: none;
  background: none;
  outline: none;
  appearance: none;
}

.css-krmsb5 {
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
  height: 61px;
  padding: 0px 30px;
}

.css-uscuon {
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
  justify-content: center;
  align-items: center;
  gap: 8px;
}

.css-uscuon1 {
  border: none;
  cursor: pointer;
  width: 77.5%;
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
  justify-content: center;
  align-items: center;
  gap: 8px;
}

.css-1lvb576 {
  font-family: Pretendard;
  font-style: normal;
  font-weight: 400;
  font-size: 14px;
  line-height: 22px;
  color: rgb(129, 137, 143);
  display: inline-flex;
  align-items: center;
}

.css-1653a83 {
  width: 1px;
  height: 16px;
  background: rgb(215, 224, 230);
  margin: 0px 22px;
}

.myprofile {
  width: 1.25rem;
  height: 25px;
}

.css-10kntrg {
  font-size: 16px;
}

.css-aw18wm {
  width: 30px;
  height: 30px;
  position: relative;
  border-radius: 100%;
  overflow: hidden;
  margin-left: 40px;
  margin-top: 23px;
}

.css-156933a {
  margin-bottom: 10px;
  margin-left: 35px;
}

.user-info {
  font-family: Pretendard, -apple-system, “system-ui”, "Malgun Gothic",
    "맑은 고딕", sans-serif;
  font-weight: 600;
  font-size: 18px;
  color: #141617;
}
/*--------로딩창-------------*/
.loadingio-spinner-spinner {
  position: fixed;
  top: 0;
  left: 0%;
  z-index: 1000; /* 다른 요소 위에 표시하기 위한 z-index 값 */
  width: 100%; /* 전체 화면을 커버 */
  height: 100%;
  justify-content: center; /* 가운데 정렬 */
  align-items: center; /* 세로 가운데 정렬 */
}
@keyframes ldio-f4nnk2ltl0v {
  0% {
    opacity: 1;
  }
  100% {
    opacity: 0;
  }
}
.ldio-f4nnk2ltl0v div {
  position: fixed;
  top: 30%;
  left: 50%;
  transform: translate(-50%, -50%);
  z-index: 999; /* 다른 요소 위에 표시하기 위한 z-index 값 */
  animation: ldio-f4nnk2ltl0v linear 1s infinite;
  background: #fe718d;
  width: 18.240000000000002px;
  height: 36.480000000000004px;
  border-radius: 9.120000000000001px / 18.240000000000002px;
  transform-origin: 9.120000000000001px 79.04px;
}
.ldio-f4nnk2ltl0v div:nth-child(1) {
  transform: rotate(0deg);
  animation-delay: -0.9166666666666666s;
  background: #fe718d;
}
.ldio-f4nnk2ltl0v div:nth-child(2) {
  transform: rotate(30deg);
  animation-delay: -0.8333333333333334s;
  background: #f47e60;
}
.ldio-f4nnk2ltl0v div:nth-child(3) {
  transform: rotate(60deg);
  animation-delay: -0.75s;
  background: #f8b26a;
}
.ldio-f4nnk2ltl0v div:nth-child(4) {
  transform: rotate(90deg);
  animation-delay: -0.6666666666666666s;
  background: #abbd81;
}
.ldio-f4nnk2ltl0v div:nth-child(5) {
  transform: rotate(120deg);
  animation-delay: -0.5833333333333334s;
  background: #849b87;
}
.ldio-f4nnk2ltl0v div:nth-child(6) {
  transform: rotate(150deg);
  animation-delay: -0.5s;
  background: #6492ac;
}
.ldio-f4nnk2ltl0v div:nth-child(7) {
  transform: rotate(180deg);
  animation-delay: -0.4166666666666667s;
  background: #637cb5;
}
.ldio-f4nnk2ltl0v div:nth-child(8) {
  transform: rotate(210deg);
  animation-delay: -0.3333333333333333s;
  background: #6a63b6;
}
.ldio-f4nnk2ltl0v div:nth-child(9) {
  transform: rotate(240deg);
  animation-delay: -0.25s;
  background: #fe718d;
}
.ldio-f4nnk2ltl0v div:nth-child(10) {
  transform: rotate(270deg);
  animation-delay: -0.16666666666666666s;
  background: #f47e60;
}
.ldio-f4nnk2ltl0v div:nth-child(11) {
  transform: rotate(300deg);
  animation-delay: -0.08333333333333333s;
  background: #f8b26a;
}
.ldio-f4nnk2ltl0v div:nth-child(12) {
  transform: rotate(330deg);
  animation-delay: 0s;
  background: #abbd81;
}
.loadingio-spinner-spinner-pz89b3jiaad {
  width: 304px;
  height: 304px;
  display: inline-block;
  overflow: hidden;
  background: #ffffff;
}
.ldio-f4nnk2ltl0v div {
  box-sizing: content-box;
}
</style>
