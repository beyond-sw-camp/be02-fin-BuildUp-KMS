<template>
  <div class="quickmenu">
    <div class="inner-quickmenu">
      <button
        class="quickmenuback btn btn-top active"
        type="button"
        aria-label="페이지 맨위로 이동"
        @click="scrollToTop()"
      >
        <svg
          viewBox="0 0 42 42"
          fill="none"
          xmlns="http://www.w3.org/2000/svg"
          class="up-icon"
        >
          <rect
            x="0.5"
            y="0.5"
            width="41"
            height="41"
            rx="20.5"
            fill="#fff"
            stroke="#E9E9E9"
          ></rect>
          <path
            d="m15 24 6-6 6 6"
            stroke="#000"
            stroke-width="1.3"
            stroke-linejoin="round"
          ></path>
        </svg>
      </button>
      <button
        class="quickmenuback btn btn-bottom active"
        type="button"
        aria-label="페이지 맨아래로 이동"
        @click="scrollToBottom()"
      >
        <svg
          viewBox="0 0 42 42"
          fill="none"
          xmlns="http://www.w3.org/2000/svg"
          class="down-icon"
        >
          <rect
            x="0.5"
            y="0.5"
            width="41"
            height="41"
            rx="20.5"
            fill="#fff"
            stroke="#E9E9E9"
          ></rect>
          <path
            d="m15 19 6 6 6-6"
            stroke="#000"
            stroke-width="1.3"
            stroke-linejoin="round"
          ></path>
        </svg>
      </button>
    </div>
  </div>
</template>

<script>
export default {
  name: "QuickMenuComponent",
  methods: {
    scrollToTop() {
      window.scrollTo({
        top: 0,
        behavior: "smooth",
      });
    },
    scrollToBottom() {
      window.scrollTo({
        top: document.body.scrollHeight,
        behavior: "smooth",
      });
    },
  },
};
</script>

<style scoped>
.quickmenu {
  position: fixed;
  right: 40px;
  bottom: 40px;
  z-index: 100;
}

.quickmenuback {
  display: inline-flex;
  flex-flow: row;
  justify-content: center;
  align-items: center;
  font-family: inherit;
  font-size: inherit;
  font-weight: inherit;
  color: inherit;
}

.quickmenu .inner-quickmenu {
  display: flex;
  flex-flow: column;
  justify-content: center;
  align-items: center;
}

.quickmenu .inner-quickmenu .btn {
  margin-top: 6px;
  transition: all 0.3s ease 0s;
  border: none;
  color: white;
  background-color: transparent;
}

.quickmenu .inner-quickmenu .btn.btn-top.active {
  opacity: 1;
  visibility: visible;
}

.up-icon {
  width: 42px;
  height: 42px;
  cursor: pointer;
}

.quickmenu .inner-quickmenu .btn.btn-bottom.active {
  margin-top: 6px;
  opacity: 1;
  visibility: visible;
}

.down-icon {
  width: 42px;
  height: 42px;
  cursor: pointer;
}
</style>
