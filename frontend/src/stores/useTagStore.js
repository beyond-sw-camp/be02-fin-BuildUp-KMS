import { defineStore } from "pinia";
import axios from "axios";

const backend = "http://localhost:8080";
const storedToken = localStorage.getItem("token");

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
        const params = new URLSearchParams({
          page: page - 1,
        }).toString();

        let response = await axios.get(backend + `/tag/list/?${params}`, {
          headers: {
            Authorization: `Bearer ${storedToken}`,
          },
        });

        this.tagList = response.data.result.list;
        this.totalPages = response.data.result.totalPages;
        this.currentPage = page;
        this.totalCnt = response.data.result.totalCnt;
      } catch (e) {
        console.log(e);
      }
    },
  },
});
