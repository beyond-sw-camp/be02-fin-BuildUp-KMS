<template>
    <AdminMenuComponent />

</template>

<script>
import { mapStores } from "pinia";
import { useAdminStore } from "/src/stores/useAdminStore";
import AdminMenuComponent from "@/components/AdminMenuComponent.vue";


export default {
    name: "AdminMainPage",
    components: {
        AdminMenuComponent,
    },
    mounted() {
        this.$root.hideHeaderAndFooter = true;
    },
    computed: {
        ...mapStores(useAdminStore),
    },
};
</script>

<style></style>