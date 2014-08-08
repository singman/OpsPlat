package com.generalsoft.singman;
import java.io.*;
import javax.swing.*;
import javax.swing.text.*;
/**
 * Created with IntelliJ IDEA.
 * User: singman
 * Date: 14-8-6
 * Time: 上午11:27
 *将控制台输出内容重定向到面板中的工具类
 */

public class ConsoleTextArea extends JTextArea {
    public ConsoleTextArea(InputStream[] inStreams) {
        for(int i = 0; i < inStreams.length; ++i)
            startConsoleReaderThread(inStreams[i]);
    } // ConsoleTextArea()
    public ConsoleTextArea() throws IOException {
        final LoopedStreams ls = new LoopedStreams();
        // 重定向System.out和System.err
        PrintStream ps = new PrintStream(ls.getOutputStream());
        System.setOut(ps);
        System.setErr(ps);
        startConsoleReaderThread(ls.getInputStream());
    } // ConsoleTextArea()
    private void startConsoleReaderThread(
            InputStream inStream) {
        final BufferedReader br =
                new BufferedReader(new InputStreamReader(inStream));
        new Thread(new Runnable() {
            public void run() {
                StringBuffer sb = new StringBuffer();
                try {
                    String s;
                    Document doc = getDocument();
                    while((s = br.readLine()) != null) {
                        boolean caretAtEnd = false;
                        caretAtEnd = getCaretPosition() == doc.getLength() ?
                                true : false;
                        sb.setLength(0);
                        append(sb.append(s).append('\n').toString());
                        if(caretAtEnd)
                            setCaretPosition(doc.getLength());
                    }
                }
                catch(IOException e) {
                    JOptionPane.showMessageDialog(null,
                            "从BufferedReader读取错误：" + e);
                    System.exit(1);
                }
            }
        }).start();
    } // startConsoleReaderThread()
} // ConsoleTextArea