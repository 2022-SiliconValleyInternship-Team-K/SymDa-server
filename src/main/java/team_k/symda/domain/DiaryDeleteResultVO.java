package team_k.symda.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiaryDeleteResultVO {
    private int flag;   // flag=0: 삭제 X, flag=1: 삭제 O
}
