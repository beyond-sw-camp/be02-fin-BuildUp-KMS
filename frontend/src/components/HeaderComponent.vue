<template>
  <header>
    <div class="css-wkr2a">
      <div class="css-we70d5">
        <div class="css-un3w91">
          <img
            src="../assets/img/logo.png"
            alt="BOOKSHELF 로고"
            class="css-huvhzc"
          />
        </div>
        <div class="css-1butcu2">
          <div class="css-1llgdoe">지식공유</div>
          <div class="css-1llgdoe">Q&A</div>
          <div class="css-1llgdoe">후기</div>
          <div class="css-1llgdoe">스터디</div>
        </div>
        <div class="css-1butcu">
          <div>
            <button class="css-login-button" @click="openLoginModal()">
              로그인
            </button>
            <button class="css-signup-button">회원가입</button>
          </div>
          <div class="css-26c0za">
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
          <div class="css-1fgr4mj">
            <div class="css-1bkawov">
              <div class="css-gfzvs2">
                <div class="css-6gvm25">
                  <div class="css-myce4z">
                    <div class="css-a72rbc"></div>
                  </div>
                  <div class="css-zqyw9">
                    <div class="css-156933a">ltw</div>
                    <div class="css-10kntrg">didididi@kakao.com</div>
                  </div>
                </div>
                <div class="css-13t7veh">
                  <div class="css-k6l58h">
                    <div class="css-ts29it">
                      <svg
                        width="16"
                        height="16"
                        viewBox="0 0 16 16"
                        fill="none"
                        xmlns="http://www.w3.org/2000/svg"
                      >
                        <g id="icon_point_fill_24">
                          <circle
                            id="Ellipse 532"
                            cx="8.75"
                            cy="8.5"
                            r="6"
                            stroke="#3A3E41"
                            stroke-width="1.5"
                          ></circle>
                          <g id="Group 2598">
                            <path
                              id="Vector 165"
                              d="M7.54956 5.96729V11.3006"
                              stroke="#3A3E41"
                              stroke-width="1.2"
                              stroke-linecap="round"
                            ></path>
                            <path
                              id="Vector 170"
                              d="M9.21623 5.96729H7.54956V9.30062H9.21623C10.3273 9.30062 10.8829 8.63395 10.8829 7.63395C10.8829 6.63395 10.3273 5.96729 9.21623 5.96729Z"
                              stroke="#3A3E41"
                              stroke-width="1.2"
                              stroke-linecap="round"
                              stroke-linejoin="round"
                            ></path>
                          </g>
                        </g>
                      </svg>
                      <div class="css-1i7yunr">포인트</div>
                    </div>
                    <div class="css-1nmvfye">로딩중</div>
                  </div>
                  <div class="css-1cuep9j"></div>
                  <div class="css-k6l58h">
                    <div class="css-ts29it">
                      <svg
                        xmlns="http://www.w3.org/2000/svg"
                        width="17"
                        height="17"
                        viewBox="0 0 17 17"
                        fill="none"
                      >
                        <mask id="path-1-inside-1_283_19679" fill="white">
                          <path
                            fill-rule="evenodd"
                            clip-rule="evenodd"
                            d="M2.08325 4.40909C2.08325 3.90701 2.49027 3.5 2.99234 3.5H14.5075C15.0096 3.5 15.4166 3.90701 15.4166 4.40909V7.16667C14.6802 7.16667 14.0833 7.76362 14.0833 8.5C14.0833 9.23638 14.6802 9.83333 15.4166 9.83333V12.5909C15.4166 13.093 15.0096 13.5 14.5075 13.5H2.99234C2.49026 13.5 2.08325 13.093 2.08325 12.5909V9.83333C2.81963 9.83333 3.41659 9.23638 3.41659 8.5C3.41659 7.76362 2.81963 7.16667 2.08325 7.16667V4.40909Z"
                          ></path>
                        </mask>
                        <path
                          d="M15.4166 7.16667V8.66667H16.9166V7.16667H15.4166ZM15.4166 9.83333H16.9166V8.33333H15.4166V9.83333ZM2.08325 9.83333V8.33333H0.583252V9.83333H2.08325ZM2.08325 7.16667H0.583252V8.66667H2.08325V7.16667ZM2.99234 2C1.66184 2 0.583252 3.07859 0.583252 4.40909H3.58325C3.58325 4.73544 3.31869 5 2.99234 5V2ZM14.5075 2H2.99234V5H14.5075V2ZM16.9166 4.40909C16.9166 3.07859 15.838 2 14.5075 2V5C14.1811 5 13.9166 4.73544 13.9166 4.40909H16.9166ZM16.9166 7.16667V4.40909H13.9166V7.16667H16.9166ZM15.5833 8.5C15.5833 8.59205 15.5086 8.66667 15.4166 8.66667V5.66667C13.8518 5.66667 12.5833 6.93519 12.5833 8.5H15.5833ZM15.4166 8.33333C15.5086 8.33333 15.5833 8.40795 15.5833 8.5H12.5833C12.5833 10.0648 13.8518 11.3333 15.4166 11.3333V8.33333ZM16.9166 12.5909V9.83333H13.9166V12.5909H16.9166ZM14.5075 15C15.838 15 16.9166 13.9214 16.9166 12.5909H13.9166C13.9166 12.2646 14.1811 12 14.5075 12V15ZM2.99234 15H14.5075V12H2.99234V15ZM0.583252 12.5909C0.583252 13.9214 1.66184 15 2.99234 15V12C3.31869 12 3.58325 12.2646 3.58325 12.5909H0.583252ZM0.583252 9.83333V12.5909H3.58325V9.83333H0.583252ZM1.91659 8.5C1.91659 8.40795 1.99121 8.33333 2.08325 8.33333V11.3333C3.64806 11.3333 4.91659 10.0648 4.91659 8.5H1.91659ZM2.08325 8.66667C1.9912 8.66667 1.91659 8.59205 1.91659 8.5H4.91659C4.91659 6.93519 3.64806 5.66667 2.08325 5.66667V8.66667ZM0.583252 4.40909V7.16667H3.58325V4.40909H0.583252Z"
                          fill="#3A3E41"
                          mask="url(#path-1-inside-1_283_19679)"
                        ></path>
                        <path
                          d="M10.0833 7.1665L7.41659 9.83317"
                          stroke="#3A3E41"
                          stroke-width="1.2"
                          stroke-linecap="round"
                          stroke-linejoin="round"
                        ></path>
                        <circle
                          cx="7.41667"
                          cy="7.16667"
                          r="0.666667"
                          fill="#3A3E41"
                        ></circle>
                        <circle
                          cx="10.0834"
                          cy="9.83317"
                          r="0.666667"
                          fill="#3A3E41"
                        ></circle>
                      </svg>
                      <div class="css-1i7yunr">쿠폰</div>
                    </div>
                    <div class="css-1nmvfye">로딩중</div>
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
                            <div class="css-17gywxx">내 강의실</div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div>
                    <div>
                      <div class="css-13fd4ke">
                        <div class="css-1jibmi3">
                          <div class="css-16regn8">
                            <div class="css-17gywxx">수강권</div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div>
                    <div>
                      <div class="css-13fd4ke">
                        <div class="css-1jibmi3">
                          <div class="css-16regn8">
                            <div class="css-17gywxx">수강증/수료증</div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div>
                    <div>
                      <div class="css-13fd4ke">
                        <div class="css-1jibmi3">
                          <div class="css-16regn8">
                            <div class="css-17gywxx">친구추천</div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div>
                    <div>
                      <div class="css-13fd4ke">
                        <div class="css-1jibmi3">
                          <div class="css-16regn8">
                            <div class="css-17gywxx">숙제 피드백</div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div>
                    <div>
                      <div class="css-13fd4ke">
                        <div class="css-1jibmi3">
                          <div class="css-16regn8">
                            <div class="css-17gywxx">계정</div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                  <a href="https://spartacodingclub.kr/nb/guide">
                    <div class="css-zbmmrt">
                      <div class="css-wwyx62">
                        <div class="css-1hq5jbp">
                          <svg
                            width="34"
                            height="24"
                            viewBox="0 0 34 24"
                            fill="none"
                            xmlns="http://www.w3.org/2000/svg"
                          >
                            <g clip-path="url(#clip0_2861_5599)">
                              <g filter="url(#filter0_di_2861_5599)">
                                <mask
                                  id="path-1-outside-1_2861_5599"
                                  maskUnits="userSpaceOnUse"
                                  x="0.686119"
                                  y="1.22205"
                                  width="18.4607"
                                  height="21.2257"
                                  fill="black"
                                >
                                  <rect
                                    fill="white"
                                    x="0.686119"
                                    y="1.22205"
                                    width="18.4607"
                                    height="21.2257"
                                  ></rect>
                                  <path
                                    d="M10.9022 20.8982C6.15254 21.2576 3.07184 18.1728 2.60973 12.0661C2.14763 5.95946 4.72905 2.44623 9.47868 2.08681C14.2283 1.7274 17.309 4.81217 17.7711 10.9188C18.2332 17.0255 15.6518 20.5387 10.9022 20.8982ZM10.6277 17.2716C12.9675 17.0945 13.8592 14.8857 13.583 11.2358C13.3068 7.5858 12.0928 5.53632 9.75311 5.71338C7.41339 5.89043 6.52163 8.09924 6.79783 11.7492C7.07403 15.3992 8.28802 17.4486 10.6277 17.2716Z"
                                  ></path>
                                </mask>
                                <path
                                  d="M10.9022 20.8982C6.15254 21.2576 3.07184 18.1728 2.60973 12.0661C2.14763 5.95946 4.72905 2.44623 9.47868 2.08681C14.2283 1.7274 17.309 4.81217 17.7711 10.9188C18.2332 17.0255 15.6518 20.5387 10.9022 20.8982ZM10.6277 17.2716C12.9675 17.0945 13.8592 14.8857 13.583 11.2358C13.3068 7.5858 12.0928 5.53632 9.75311 5.71338C7.41339 5.89043 6.52163 8.09924 6.79783 11.7492C7.07403 15.3992 8.28802 17.4486 10.6277 17.2716Z"
                                  fill="url(#paint0_linear_2861_5599)"
                                  shape-rendering="crispEdges"
                                ></path>
                                <path
                                  d="M10.8859 20.6825C8.56479 20.8582 6.67445 20.193 5.30455 18.7576C3.92908 17.3164 3.05414 15.0733 2.82535 12.0498L2.39412 12.0824C2.62743 15.1657 3.52389 17.5182 4.9917 19.0562C6.46509 20.6 8.48992 21.2975 10.9185 21.1138L10.8859 20.6825ZM2.82535 12.0498C2.59656 9.02636 3.12398 6.6771 4.26691 5.0453C5.40521 3.42012 7.17393 2.47807 9.49499 2.30243L9.46236 1.8712C7.03379 2.05497 5.13699 3.04924 3.91269 4.7972C2.69304 6.53854 2.1608 8.99923 2.39412 12.0824L2.82535 12.0498ZM9.49499 2.30243C11.8161 2.12679 13.7064 2.79195 15.0763 4.22733C16.4518 5.66856 17.3267 7.9117 17.5555 10.9352L17.9867 10.9025C17.7534 7.8193 16.857 5.46673 15.3891 3.92875C13.9158 2.38493 11.8909 1.68742 9.46236 1.8712L9.49499 2.30243ZM17.5555 10.9352C17.7843 13.9586 17.2569 16.3079 16.1139 17.9397C14.9756 19.5648 13.2069 20.5069 10.8859 20.6825L10.9185 21.1138C13.3471 20.93 15.2439 19.9357 16.4681 18.1878C17.6878 16.4464 18.22 13.9857 17.9867 10.9025L17.5555 10.9352ZM10.6441 17.4872C11.8955 17.3925 12.77 16.7455 13.2823 15.6474C13.7864 14.5672 13.9378 13.0584 13.7986 11.2194L13.3674 11.2521C13.5044 13.0631 13.3481 14.4837 12.8904 15.4646C12.441 16.4277 11.6997 16.9736 10.6114 17.056L10.6441 17.4872ZM13.7986 11.2194C13.6595 9.38047 13.2827 7.91169 12.6218 6.91956C11.9501 5.91115 10.9882 5.40306 9.73679 5.49776L9.76942 5.92899C10.8577 5.84664 11.6727 6.27476 12.2619 7.15931C12.862 8.06014 13.2304 9.44108 13.3674 11.2521L13.7986 11.2194ZM9.73679 5.49776C8.48536 5.59246 7.61089 6.23951 7.09855 7.33752C6.59447 8.41781 6.44306 9.92655 6.58221 11.7655L7.01345 11.7329C6.8764 9.9219 7.03276 8.50125 7.49045 7.52038C7.93987 6.55723 8.68113 6.01134 9.76942 5.92899L9.73679 5.49776ZM6.58221 11.7655C6.72137 13.6045 7.09811 15.0733 7.75901 16.0654C8.43074 17.0738 9.39262 17.5819 10.6441 17.4872L10.6114 17.056C9.52314 17.1383 8.70816 16.7102 8.11892 15.8256C7.51885 14.9248 7.15049 13.5439 7.01345 11.7329L6.58221 11.7655Z"
                                  fill="#30725D"
                                  fill-opacity="0.6"
                                  mask="url(#path-1-outside-1_2861_5599)"
                                ></path>
                              </g>
                              <g filter="url(#filter1_d_2861_5599)">
                                <path
                                  d="M31.6176 17.362L29.3676 17.1676L29.5161 15.4494C28.3743 15.6255 26.901 15.6631 25.3507 15.6391L25.2246 17.0982L23.1382 16.9179L23.0722 17.6816L31.5269 18.412L31.3478 20.4848L20.4386 19.5423L20.5658 18.0695L19.8022 18.0035L19.9695 16.0671L22.7513 16.3075L22.8161 15.5575C21.6126 15.4947 20.4535 15.3946 19.4307 15.3062L19.598 13.3698C22.8435 13.6502 26.9084 13.9877 29.6834 13.513L29.7917 12.2585L28.3463 12.1336L28.5277 10.0335L29.9732 10.1584L30.0875 8.83567L32.3375 9.03007L31.6176 17.362ZM20.2261 10.5528C20.3592 9.01182 21.669 8.16329 24.3963 8.39892C27.1373 8.63574 28.3093 9.69869 28.1762 11.2396C28.0431 12.7806 26.706 13.6267 23.9651 13.3899C21.2378 13.1543 20.093 12.0937 20.2261 10.5528ZM22.6125 10.7589C22.5665 11.2908 23.2639 11.4884 24.123 11.5626C25.0093 11.6392 25.7178 11.5493 25.7625 11.0311C25.8085 10.4993 25.1112 10.3016 24.2384 10.2262C23.3793 10.152 22.6584 10.2271 22.6125 10.7589Z"
                                  fill="url(#paint1_linear_2861_5599)"
                                ></path>
                              </g>
                            </g>
                            <defs>
                              <filter
                                id="filter0_di_2861_5599"
                                x="-0.696834"
                                y="-1.08162"
                                width="21.7745"
                                height="25.1482"
                                filterUnits="userSpaceOnUse"
                                color-interpolation-filters="sRGB"
                              >
                                <feFlood
                                  flood-opacity="0"
                                  result="BackgroundImageFix"
                                ></feFlood>
                                <feColorMatrix
                                  in="SourceAlpha"
                                  type="matrix"
                                  values="0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 127 0"
                                  result="hardAlpha"
                                ></feColorMatrix>
                                <feOffset></feOffset>
                                <feGaussianBlur
                                  stdDeviation="1.29739"
                                ></feGaussianBlur>
                                <feComposite
                                  in2="hardAlpha"
                                  operator="out"
                                ></feComposite>
                                <feColorMatrix
                                  type="matrix"
                                  values="0 0 0 0 0.188235 0 0 0 0 0.447059 0 0 0 0 0.364706 0 0 0 0.25 0"
                                ></feColorMatrix>
                                <feBlend
                                  mode="normal"
                                  in2="BackgroundImageFix"
                                  result="effect1_dropShadow_2861_5599"
                                ></feBlend>
                                <feBlend
                                  mode="normal"
                                  in="SourceGraphic"
                                  in2="effect1_dropShadow_2861_5599"
                                  result="shape"
                                ></feBlend>
                                <feColorMatrix
                                  in="SourceAlpha"
                                  type="matrix"
                                  values="0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 127 0"
                                  result="hardAlpha"
                                ></feColorMatrix>
                                <feOffset
                                  dx="0.864928"
                                  dy="0.648696"
                                ></feOffset>
                                <feGaussianBlur
                                  stdDeviation="0.432464"
                                ></feGaussianBlur>
                                <feComposite
                                  in2="hardAlpha"
                                  operator="arithmetic"
                                  k2="-1"
                                  k3="1"
                                ></feComposite>
                                <feColorMatrix
                                  type="matrix"
                                  values="0 0 0 0 1 0 0 0 0 1 0 0 0 0 1 0 0 0 0.8 0"
                                ></feColorMatrix>
                                <feBlend
                                  mode="normal"
                                  in2="shape"
                                  result="effect2_innerShadow_2861_5599"
                                ></feBlend>
                              </filter>
                              <filter
                                id="filter1_d_2861_5599"
                                x="16.4802"
                                y="5.37494"
                                width="18.4521"
                                height="17.7046"
                                filterUnits="userSpaceOnUse"
                                color-interpolation-filters="sRGB"
                              >
                                <feFlood
                                  flood-opacity="0"
                                  result="BackgroundImageFix"
                                ></feFlood>
                                <feColorMatrix
                                  in="SourceAlpha"
                                  type="matrix"
                                  values="0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 127 0"
                                  result="hardAlpha"
                                ></feColorMatrix>
                                <feOffset></feOffset>
                                <feGaussianBlur
                                  stdDeviation="1.29739"
                                ></feGaussianBlur>
                                <feComposite
                                  in2="hardAlpha"
                                  operator="out"
                                ></feComposite>
                                <feColorMatrix
                                  type="matrix"
                                  values="0 0 0 0 0.187622 0 0 0 0 0.445833 0 0 0 0 0.364544 0 0 0 0.25 0"
                                ></feColorMatrix>
                                <feBlend
                                  mode="normal"
                                  in2="BackgroundImageFix"
                                  result="effect1_dropShadow_2861_5599"
                                ></feBlend>
                                <feBlend
                                  mode="normal"
                                  in="SourceGraphic"
                                  in2="effect1_dropShadow_2861_5599"
                                  result="shape"
                                ></feBlend>
                              </filter>
                              <linearGradient
                                id="paint0_linear_2861_5599"
                                x1="9.28318"
                                y1="-3.16004"
                                x2="11.7732"
                                y2="29.7459"
                                gradientUnits="userSpaceOnUse"
                              >
                                <stop stop-color="#30725D"></stop>
                                <stop
                                  offset="1"
                                  stop-color="#73CFB2"
                                  stop-opacity="0.4"
                                ></stop>
                              </linearGradient>
                              <linearGradient
                                id="paint1_linear_2861_5599"
                                x1="26.7539"
                                y1="5.66563"
                                x2="24.5817"
                                y2="30.8082"
                                gradientUnits="userSpaceOnUse"
                              >
                                <stop stop-color="#30725D"></stop>
                                <stop offset="1" stop-color="#73CFB2"></stop>
                              </linearGradient>
                              <clipPath id="clip0_2861_5599">
                                <rect
                                  width="32.651"
                                  height="23.1368"
                                  fill="white"
                                  transform="translate(0.875 0.0467911)"
                                ></rect>
                              </clipPath>
                            </defs>
                          </svg>
                        </div>
                        <div class="css-1bku0gi">
                          <p class="css-1smjha1">50만원 강의를 0원으로?</p>
                          <p class="css-3aha2l">국비지원 신청 가이드 보기</p>
                        </div>
                      </div>
                      <svg
                        width="16"
                        height="16"
                        viewBox="0 0 16 16"
                        fill="none"
                        xmlns="http://www.w3.org/2000/svg"
                      >
                        <path
                          d="M6.66565 12L10.6656 8L6.66565 4"
                          stroke="#9da7ae"
                          stroke-width="1.5"
                          stroke-linecap="round"
                          stroke-linejoin="round"
                        ></path>
                      </svg>
                    </div>
                  </a>
                </div>
                <div class="css-1o9qtii"></div>
                <div class="css-1s9cv9y">
                  <div class="css-ik70s9">
                    <div class="css-1jibmi3">
                      <div class="css-28a73i">
                        <div class="css-4l7ba3">로그아웃</div>
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
          <button color="#141617" class="css-18wq8ro">
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
          <div direction="vertical" size="40" class="css-ygt1wz"></div>
          <input
            placeholder="이메일 입력"
            type="string"
            autocapitalize="off"
            class="login-custom-input css-krmsb5"
            value=""
          />
          <div direction="vertical" size="12" class="css-j86f64"></div>
          <input
            placeholder="비밀번호 입력"
            type="password"
            autocapitalize="off"
            class="login-custom-input css-krmsb5"
            value=""
          />
          <div direction="vertical" size="40" class="css-ygt1wz"></div>
          <button color="#FFFFFF" class="css-uscuon">로그인</button>
        </div>
        <div class="css-1sfln3b">
          <div class="css-1lvb576">
            <span
              color="#81898F"
              class="css-1cap2q"
              style="text-decoration: none"
              >회원가입</span
            >
            <div class="css-1653a83"></div>
            <span
              color="#81898F"
              class="css-1cap2q"
              style="text-decoration: none"
              >이메일 찾기</span
            >
            <div class="css-1653a83"></div>
            <span
              color="#81898F"
              class="css-1cap2q"
              style="text-decoration: none"
              >비밀번호 찾기</span
            >
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "HeaderComponent",
  data() {
    return {
      isLogin: false,
      isEmailLogin: false,
    };
  },
  methods: {
    openLoginModal() {
      console.log("click");
      this.isLogin = true;
    },
    closeLoginModal() {
      this.isLogin = false;
    },
    openEmailLoginModal() {
      this.isEmailLogin = true;
    },
    closeEmailLoginModal() {
      this.isEmailLogin = false;
    },
    closeAllLoginModal() {
      this.isLogin = false;
      this.isEmailLogin = false;
    },
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
  height: auto; /* 이미지의 높이를 자동 조정하여 헤더와 잘 맞게 배치 */
  max-height: 100%; /* 이미지가 너무 크게 확대되지 않도록 제한 */
  max-width: 120px; /* 이미지의 최대 너비를 설정하여 헤더와 너무 커지지 않도록 제한 */
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
}

.css-wkr2a ::before,
.css-wkr2a ::after {
  box-sizing: inherit;
}

.css-wkr2a {
  z-index: 20;
  display: flex;
  align-items: center;
  justify-content: center; /* 헤더 내의 요소들을 수평 중앙으로 정렬합니다. */
  transition: all 0.4s ease 0s;
  background-color: white;
  height: 70px;
  width: 100%;
  border-bottom: 1px solid rgb(228, 235, 240);
  position: fixed;
  top: 0; /* 헤더를 화면 상단에 고정시킵니다. */
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
  justify-content: space-between; /* 요소들을 양쪽 끝에 배치 */
  align-items: center; /* 세로 중앙 정렬 */
  margin: 0 auto; /* 가운데 정렬을 위해 margin을 0으로 설정 */
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

.css-1fgr4mj {
  display: block;
  padding: 32px 0 0 0;
  border: #d7e0e6 solid 1px;
  border-radius: 10px;
  position: absolute;
  top: calc(40px + 0px);
  right: 0;
  background-color: white;
  opacity: 0;
  visibility: hidden;
  z-index: unset;
  width: 282px;
  transform: translateX(-50%);
  box-shadow: 0px 8px 24px rgba(95, 102, 107, 0.12);
  font-family: "Pretendard";
  overflow: scroll;
  scrollbar-width: none;
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

.css-1fgr4mj {
  display: block;
  padding: 32px 0 0 0;
  border: #d7e0e6 solid 1px;
  border-radius: 10px;
  position: absolute;
  top: calc(40px + 0px);
  right: 0;
  background-color: white;
  opacity: 0;
  visibility: hidden;
  z-index: unset;
  width: 282px;
  -webkit-transform: translateX(-50%);
  -moz-transform: translateX(-50%);
  -ms-transform: translateX(-50%);
  transform: translateX(-50%);
  box-shadow: 0px 8px 24px rgba(95, 102, 107, 0.12);
  font-family: "Pretendard";
  overflow: scroll;
  scrollbar-width: none;
  -ms-overflow-style: none;
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
  font-size: 13px;
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

  .css-1fgr4mj {
    width: 100%;
    height: calc(100dvh - 50px - 0px);
    position: fixed;
    top: calc(49px + 0px);
    right: 0;
    background-color: white;
    opacity: 0;
    visibility: hidden;
    z-index: unset;
    overflow: scroll;
    border-radius: 0;
    border: none;
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

.css-kcgo1q {
  display: flex;
  flex-direction: column;
  -webkit-box-align: center;
  align-items: center;
  text-align: center;
  width: 100%;
  padding-top: 30px;
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
  padding-top: 100px;
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
  padding: 0px 24px;
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
</style>
