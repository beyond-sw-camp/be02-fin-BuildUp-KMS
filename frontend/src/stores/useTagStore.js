import { defineStore } from "pinia";
import axios from "axios";

const backend = "http://localhost:8080";

export const useTagStore = defineStore("tag", {
  state: () => ({
    tagList: [],
    currentPage: 0,
    totalPages: 0,
    totalCnt: 0,
  }),
  actions: {
    async getTagList(page = 1) {
      try {
        let response = await axios.get(backend + "/admin/tag/list?page=" + (page - 1));

        this.tagList = response.data.result.list;
        this.totalPages = response.data.result.totalPages;
        this.currentPage = page;
        this.totalCnt = response.data.result.totalCnt;

        console.log(response);
      } catch (e) {
        console.log(e);
      }
    },
  },
});
