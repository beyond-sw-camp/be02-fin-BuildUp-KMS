<template>
    <AdminMenuComponent />

</template>

<script>
import AdminMenuComponent from "@/components/AdminMenuComponent.vue";


export default {
    name: "AdminMainPage",
    components: {
        AdminMenuComponent,
    },
    mounted() {
        this.$root.hideHeaderAndFooter = true;
    },
};
</script>

<style></style>