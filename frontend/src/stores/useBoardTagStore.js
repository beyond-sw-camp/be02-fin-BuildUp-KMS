import { defineStore } from "pinia";
import axios from "axios";

      // const backend = "http://192.168.0.61/api";
      const backend = "http://localhost:8080";

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


