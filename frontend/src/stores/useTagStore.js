import { defineStore } from "pinia";
import axios from "axios";

// const backend = "http://192.168.0.82/api";
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

      } catch (e) {
        console.log(e);
      }
    },

    async createTag(tagName) {
      try {
        await axios.post(backend + "/admin/tag/create", { tagName: tagName }, {
          headers: {
            "Content-Type": "application/json",
          }
        });
      } catch (e) {
        console.error(e);
        throw e;
      }
    },

    async updateTag(tagIdx, newCategoryName) {
      try {
        await axios.patch(backend + "/admin/tag/update/", {
          tagIdx: tagIdx,
          tagName: newCategoryName
        })
      } catch(e) {
        console.error(e);
        throw e;
      }
    },

    async deleteTag(tagIdx) {
      try {
        let response = await axios.delete(backend + "/admin/tag/delete/" + tagIdx);
        return response;
      } catch (e) {
        console.error(e);
        throw e;
      }
    },
  },
});
