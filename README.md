# 端末位置別に分割されたHASCデータを合体させるプログラム


## 使い方
1. MergeTerminalPosition_for_HASC.javaを適当なティレクトリにコピー
2. MergeTerminalPosition_for_HASC.javaがある階層にcdで移動
3. MergeTerminalPosition_for_HASC.javaと同ディレクトリにdataディレクトリを用意.
4. dataディレクトリにHASCコーパスのデータを入れる.
5. 以下のコマンドを実行
6. outputディレクトリが作成され, そこに合体したデータが出力される.

##### コマンド
```
javac MergeTerminalPosition_for_HASC.java
java MergeTerminalPosition_for_HASC [端末位置1] [端末位置2] ... [合体後の姿勢名]

ex.
java MergeTerminalPosition_for_HASC wear;pants;waist;front wear;pants;waist;right-front wear;pants;waist;
```


### データのディレクトリ構成
```
.  
data  
├ wear;pants;waist;right-front
    ├── 1_stay
    │  ├── person0001
    │  │   ├── HASCXXXXXX-acc.csv
    │  │   ├── HASCXXXXXX-gyro.csv
    │  │   ├── HASCXXXXXX-mag.csv
    │  │   └── ...
    │  ├── person0002
    │  └── ...
    ├── 2_walk
    │　├── person0001
    │　│   ├── HASCXXXXXX-acc.csv
    │　│   ├── HASCXXXXXX-gyro.csv
    │　│   ├── HASCXXXXXX-mag.csv
    │　│   └── ...
    │　├── person0002
    │　└── ...
    └── ...
```


### 　
Developed by icchi  
2016/06/21
