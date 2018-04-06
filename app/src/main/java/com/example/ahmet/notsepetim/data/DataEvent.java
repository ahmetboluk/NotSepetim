package com.example.ahmet.notsepetim.data;

public class DataEvent {
    public static class AddNoteDialogShow{
        private int fire;
        public int getFire() {
            return fire;
        }
        public void setFire(int fire) {
            this.fire = fire;
        }
        public AddNoteDialogShow(int fire){
            this.fire = fire;
        }
    }
    public static class DataRefreshFire{
        private int fire;
        public int getFire() {
            return fire;
        }
        public void setFire(int fire) {
            this.fire = fire;
        }
        public DataRefreshFire(int fire){
            this.fire = fire;
        }
    }
    public static class SwipeFire{
        private int fire;
        public int getFire() {
            return fire;
        }
        public void setFire(int fire) {
            this.fire = fire;
        }
        public SwipeFire(int fire){
            this.fire = fire;
        }
    }

    public  static class NoteCompleteShowFragment{
        private int fire;
        public int getFire() { return fire; }
        public void setFire(int fire) { this.fire = fire; }
        public NoteCompleteShowFragment(int fire) { this.fire = fire; }
    }
    public  static class NoteCompleteShowPosition{
        private int fire;
        public int getFire() { return fire; }
        public void setFire(int fire) { this.fire = fire; }
        public NoteCompleteShowPosition(int fire) { this.fire = fire; }
    }
}
