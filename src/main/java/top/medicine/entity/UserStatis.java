package top.medicine.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserStatis {

    public SexStatis sex;
    public MedicineStatis medicine;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SexStatis {
        public int l18; //0<=x<=18
        public int l30; //18<=x<=30
        public int l48; //30<=x<=48
        public int l65; //48<=x<=65
        public int l80; //65<=x<=80
        public int l95; //80<=x<=95
        public int m95; //95<=x<=120
    }

    @Data
    public static class MedicineStatis {
        public int a;
        public int b;
        public int c;
    }
}