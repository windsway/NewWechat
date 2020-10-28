package com.demo.wechat.bean;

public  class Comment {
        /**
         * content : Good.
         * sender : {"username":"outman","nick":"Super hero","avatar":"https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcRJm8UXZ0mYtjv1a48RKkFkdyd4kOWLJB0o_l7GuTS8-q8VF64w"}
         */

        private String content;
        private User sender;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public void setSender(User sender) {
            this.sender = sender;
        }

        public User getSender() {
            return sender;
        }
        
    }