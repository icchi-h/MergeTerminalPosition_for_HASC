/*
* Title： MergeTerminalPosition_for_HASC
* 説明 ： 端末位置別に分割されたHASCデータを合体されるプログラム. 結合させるディレクトリ名を引数として記述
* @date Created on: 2016/06/21
* @author Author: Haruyuki Ichino
* @version 1.0
*
*/


import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.*;

public class MergeTerminalPosition_for_HASC {

    // 出力データの格納場所
    static String output_path = "./output/";


    public static void main(String[] args) {

        // もし出力フォルダがなければ作成
        String output_terminalPosition = args[args.length-1];
        File output_dir = new File(output_path);
        if(output_dir.exists() == false){
            output_dir.mkdir();
        }
        File output_terminalPosition_dir = new File(output_dir.getPath()+"/"+output_terminalPosition+"/");
        if(output_terminalPosition_dir.exists() == false){
            output_terminalPosition_dir.mkdir();
        }

        // 通常のファイル(隠しファイルでない)のみを取り出すフィルタの作成
        FilenameFilter normalFileFilter = new FilenameFilter() {
            public boolean accept(File file, String name) {
                if (file.isHidden() == false){
                    return true;
                } else {
                    return false;
                }
            }
        };


        // 各引数(端末部位文字列)にアクセスして処理
        for(int i=0; i<args.length-1; i++){

            // 引数の端末位置を取得
            String terminalPosition = args[i];

            System.out.println("========================================================================");
            System.out.println(terminalPosition);
            System.out.println("========================================================================");
            File data_dir = new File(terminalPosition+"/");


            // data内のファイルを取得
            File[] activity_dirs = data_dir.listFiles(normalFileFilter);

            try {
                System.out.println("Activity count = " + activity_dirs.length);
            } catch(NullPointerException ex){
                System.out.println("引数がおかしいかも");
                System.exit(-1);
            }

            // 各行動ディレクトリにアクセス
            for (File activity_dir : activity_dirs){
                if(activity_dir.isHidden() == false){
                    System.out.println("===================================================");
                    System.out.println(activity_dir);
                    System.out.println("===================================================");

                    // 行動ディレクトリ内のファイルを取得
                    File[] person_dirs = activity_dir.listFiles(normalFileFilter);

                    System.out.println("person count = " + person_dirs.length);

                    // 各personディレクトリにアクセス
                    for(File person_dir : person_dirs){
                        if(person_dir.isHidden() == false){
                            System.out.println("======================================");
                            System.out.println(person_dir.getName());
                            System.out.println("======================================");

                            // personディレクトリ内のファイルを取得
                            File[] files = person_dir.listFiles(normalFileFilter);

                            // 出力ディレクトリに行動ディレクトリを作成
                            File output_activity_dir = new File(output_terminalPosition_dir.getPath()+"/"+activity_dir.getName()+"/");
                            if(output_activity_dir.exists() == false){
                                output_activity_dir.mkdir();
                            }
                            // 出力ディレクトリにpersonディレクトリを作成
                            File output_person_dir = new File(output_activity_dir.getPath()+"/"+person_dir.getName()+"/");
                            if(output_person_dir.exists() == false){
                                output_person_dir.mkdir();
                            }


                            // 各ファイルにアクセス
                            for(File file : files){

                                // 入力ファイルのパス設定
                                Path filePath = FileSystems.getDefault().getPath(file.getPath());
                                // 出力ファイルのパス設定
                                Path output_filePath = FileSystems.getDefault().getPath(output_person_dir.getPath()+"/"+file.getName());

                                // コピー処理
                                try {
                                    Files.copy(filePath, output_filePath);
                                    System.out.println("コピー完了: "+output_filePath.toString());
                                } catch (FileAlreadyExistsException e){
                                    System.out.println("\tError: ファイルがすでに存在しています!");
                                } catch (NoSuchFileException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }
            }

        }

    }
}
