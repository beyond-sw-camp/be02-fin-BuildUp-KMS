<template>
  <div v-if="isVisible" class="confirm-dialog">
    <div class="dialog-content">
      <p>{{ message }}</p>
      <button @click="confirm">확인</button>
      <button @click="cancel">취소</button>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    isVisible: Boolean,
    message: String,
    onConfirm: Function,
    onCancel: Function,
  },
  methods: {
    confirm() {
      console.log("confirm method called");
      this.onConfirm();
    },
    cancel() {
      this.onCancel();
    },
  },
};
</script>

<style scoped>
.confirm-dialog {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
}

.dialog-content {
  background: white;
  padding: 20px;
  border-radius: 8px;
  text-align: center;
}
</style>
