package com.zkingsoft.util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class BuildMatrix implements ActionListener {
	/**
	 * 老项目所在目录
	 */
	private String source;
	/**
	 * 新项目所在目录
	 */
	private String target;
	/**
	 * 老项目名称
	 */
	private String oldProjectName;
	/**
	 * 新项目名称
	 */
	private String newProjectName;

	private String info = "调试信息";
	JPanel leftJp = new JPanel();
	JPanel rightJp = new JPanel();
	JTextField t1 = new JTextField();
	JTextField t2 = new JTextField();
	JTextField t3 = new JTextField("matrix");
	JTextField t4 = new JTextField();
	JTextArea msgArea = new JTextArea(10, 200);
	JButton buildButton = new JButton("Matrix项目工具");

	public BuildMatrix() {

		// 确保一个漂亮的外观风格
		JFrame.setDefaultLookAndFeelDecorated(true);

		// 创建及设置窗口
		JFrame frame = new JFrame("Matrix 项目构建");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 500);

		frame.setLayout(new BorderLayout(20, 10));

		leftJp.setLayout(new FlowLayout());

		leftJp.add(buildButton);
		leftJp.setBackground(new Color(227, 238, 254));
		rightJp.setBackground(new Color(255, 255, 255));
		rightJp.setLayout(null);

		t1.setBounds(100, 20, 250, 20);
		t2.setBounds(100, 60, 250, 20);
		t3.setBounds(100, 100, 250, 20);
		t4.setBounds(100, 140, 250, 20);

		JLabel l1 = new JLabel("源项目路径");
		l1.setBounds(25, 20, 160, 20);
		JLabel l2 = new JLabel("新项目路径");
		l2.setBounds(25, 60, 160, 20);
		JLabel l3 = new JLabel("源项目名称");
		l3.setBounds(25, 100, 160, 20);
		JLabel l4 = new JLabel("新项目名称");
		l4.setBounds(25, 140, 160, 20);

		JButton sub1 = new JButton("开始构建");
		sub1.setBounds(25, 180, 160, 20);
		sub1.addActionListener(this);

		JScrollPane scollpane = new JScrollPane(msgArea);

		scollpane.setBounds(25, 220, 400, 200);
		msgArea.setEditable(false);
		msgArea.setText(info);
		rightJp.add(scollpane);
		rightJp.add(l1);
		rightJp.add(l2);
		rightJp.add(l3);
		rightJp.add(l4);

		rightJp.add(sub1);

		rightJp.add(t1);
		rightJp.add(t2);
		rightJp.add(t3);
		rightJp.add(t4);
		frame.add(leftJp, "West");
		frame.add(rightJp, "Center");
		frame.setLocationRelativeTo(null);
		// 显示窗口
		frame.setVisible(true);
	}

	/**
	 * 构建一个新的matrix项目
	 * 
	 * @throws Exception
	 */
	public void build() {
		// 递归复制文件
		copyFile(source, target);
	}

	/**
	 * 
	 * @param sourcePath
	 * @param targetPath
	 * @throws Exception
	 */
	private void copyFile(String sourcePath, String targetPath) {
		File sourceFile = new File(sourcePath);
		if (sourceFile.isDirectory()) {
			File[] listFiles = sourceFile.listFiles();
			for (File file : listFiles) {
				copyFile(file.getPath(), targetPath);
			}
		} else {
			fileCopy(sourceFile);
		}
	}

	private void fileCopy(File srcFile) {

		String srcPath = srcFile.getPath();

		String projectName = "";
		String pomFile = "";
		String webxmlFile = "";
		Integer index = new Integer(0);
		if ((index = srcPath.indexOf(oldProjectName + "-base")) > 0) {
			projectName = newProjectName + "-base";
		} else if ((index = srcPath.indexOf(oldProjectName + "-tools")) > 0) {
			projectName = newProjectName + "-tools";
		} else if ((index = srcPath.indexOf(oldProjectName + "-dao")) > 0) {
			projectName = newProjectName + "-dao";
		} else if ((index = srcPath.indexOf(oldProjectName + "-service")) > 0) {
			projectName = newProjectName + "-service";
		} else if ((index = srcPath.indexOf(oldProjectName + "-web")) > 0) {
			projectName = newProjectName + "-web";
		}

		if (index > 0) {
			String des = target + File.separator + projectName + srcPath
					.substring(index + projectName.length() - projectName.indexOf("-") + oldProjectName.length());
			createFile(des);
			if (srcFile.getName().equals("pom.xml") || srcFile.getName().equals(".project")) {
				pomFile = des;
			} else if (srcFile.getName().equals("web.xml")) {
				webxmlFile = des;
			}
			BufferedInputStream in = null;
			BufferedOutputStream out = null;
			try {
				in = new BufferedInputStream(new DataInputStream(new FileInputStream(srcFile)));
				out = new BufferedOutputStream(new DataOutputStream(new FileOutputStream(des)));
				byte[] b = new byte[1024];
				int length = 0;
				while ((length = in.read(b)) != -1) {
					out.write(b, 0, length);
				}
				out.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (in != null)
						in.close();
					if (out != null)
						out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			try {
				// 判断是否需要修改文件
				if (pomFile.length() > 0) {
					BufferedReader br = new BufferedReader(new FileReader(srcFile));
					PrintWriter bw = new PrintWriter(new FileWriter(pomFile));
					String s = "";
					while ((s = br.readLine()) != null) {
						String tmp = new String(s.toString().getBytes("UTF-8"));
						tmp = tmp.replaceAll(oldProjectName, newProjectName);
						bw.println(tmp);
					}
					br.close();
					bw.close();
				} else if (webxmlFile.length() > 0) {
					BufferedReader br = new BufferedReader(new FileReader(srcFile));
					PrintWriter bw = new PrintWriter(new FileWriter(webxmlFile));
					String s = "";
					while ((s = br.readLine()) != null) {
						String tmp = new String(s.toString().getBytes("UTF-8"));
						tmp.replaceAll("<param-value>webName.root11</param-value>",
								"<param-value>webName." + UUID.randomUUID() + "</param-value>");
						bw.println(tmp);
					}
					br.close();
					bw.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		new BuildMatrix();
	}

	/**
	 * 创建一个文件夹
	 * 
	 * @param path
	 * @throws Exception
	 */
	private void createFile(String filePath) {
		File file = new File(filePath);
		if (file.exists()) {// 判断文件是否存在
			System.out.println("目标文件已存在" + filePath);
			msgArea.setText(msgArea.getText() + "\r\n" + "目标文件已存在" + filePath);
		}
		if (filePath.endsWith(File.separator)) {// 判断文件是否为目录
			System.out.println("目标文件不能为目录！");
			msgArea.setText(msgArea.getText() + "\r\n" + "目标文件不能为目录！" + filePath);
			rightJp.repaint();
		}
		if (!file.getParentFile().exists()) {// 判断目标文件所在的目录是否存在
			// 如果目标文件所在的文件夹不存在，则创建父文件夹
			System.out.println("目标文件所在目录不存在，准备创建它！");
			if (!file.getParentFile().mkdirs()) {// 判断创建目录是否成功
				System.out.println("创建目标文件所在的目录失败！");
				msgArea.setText(msgArea.getText() + "\r\n" + "创建目标文件所在的目录失败！");
				rightJp.repaint();
			}
		}
		try {
			if (file.createNewFile()) {// 创建目标文件
				System.out.println("创建文件成功:" + filePath);
				msgArea.setText(msgArea.getText() + "\r\n" + "创建文件成功:" + filePath);
				rightJp.repaint();
			} else {
				System.out.println("创建文件失败！" + filePath);
				msgArea.setText(msgArea.getText() + "\r\n" + "创建文件失败！" + filePath);
				rightJp.repaint();
			}
		} catch (IOException e) {// 捕获异常
			e.printStackTrace();
			msgArea.setText(msgArea.getText() + "\r\n" + "创建文件失败！" + e.getMessage());
			rightJp.repaint();
			System.out.println("创建文件失败！" + e.getMessage());
		}
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getOldProjectName() {
		return oldProjectName;
	}

	public void setOldProjectName(String oldProjectName) {
		this.oldProjectName = oldProjectName;
	}

	public String getNewProjectName() {
		return newProjectName;
	}

	public void setNewProjectName(String newProjectName) {
		this.newProjectName = newProjectName;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String sourceEvent = e.getActionCommand();
		if (sourceEvent.equals("开始构建")) {
			setSource(t1.getText());
			setTarget(t2.getText());
			setOldProjectName(t3.getText());
			setNewProjectName(t4.getText());
			if (getSource().equals("") || getTarget().equals("") || getOldProjectName().equals("")
					|| getNewProjectName().equals("")) {
				JOptionPane.showMessageDialog(null, "需要填写所有的文本框", "警告", JOptionPane.WARNING_MESSAGE);
			} else {
				msgArea.setText(msgArea.getText() + "\r\n" + "start-----");
				rightJp.repaint();
				new Thread(new Runnable() {

					@Override
					public void run() {
						build();
						msgArea.setText(msgArea.getText() + "\r\n" + "end----");
						rightJp.repaint();
					}
				}).start();
				rightJp.repaint();
			}
		}

	}
}
