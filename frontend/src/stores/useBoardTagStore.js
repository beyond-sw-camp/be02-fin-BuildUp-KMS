import { defineStore } from "pinia";
import axios from "axios";

const backend = "http://frontend-svc:8080";
// const storedToken = localStorage.getItem("token");

export const useBoardTagStore = defineStore("boardTag", {
  state: () => ({
    hotTagList: [],
  }),
  actions: {

    async getHotTagList() {
      try {

        let response = await axios.get(
          backend + `/boardtag/list`
        );

        this.hotTagList = response.data.result;

      } catch (e) {
        console.log(e);
      }
    },
  },
});
