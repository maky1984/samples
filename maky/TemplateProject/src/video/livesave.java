package video;

/*
 javac livesave.java
 java livesave
 Check the path is found
 String path1="file:///c:\\sam.avi"  path where the file has to be saved.
 Install device driver and java media frame work before running this 
 program
 */

import java.awt.Frame;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.media.CaptureDeviceInfo;
import javax.media.CaptureDeviceManager;
import javax.media.DataSink;
import javax.media.Format;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Player;
import javax.media.Processor;
import javax.media.ProcessorModel;
import javax.media.control.TrackControl;
import javax.media.format.AudioFormat;
import javax.media.format.VideoFormat;
import javax.media.protocol.DataSource;
import javax.media.protocol.FileTypeDescriptor;

class live1 extends Frame

{
	Player aplayer, vplayer;
	Panel panel;
	CaptureDeviceInfo audiodevice;
	CaptureDeviceInfo videodevice, deviceinfo;
	Format videoaudioformat = null;
	String devicename[] = new String[10];
	Format audioformat = null;
	Format videoformat = null;
	Processor processor1, processor2;
	ProcessorModel processormodel;
	Object state = new Object();
	DataSource ds;
	int a = 1;
	MediaLocator videolocator, audiolocator;
	DataSource datasource[] = new DataSource[2];
	Vector devicelist;

	public live1() {
		try {
			devicelist = CaptureDeviceManager.getDeviceList(videoaudioformat);
			for (int i = 0; i < devicelist.size(); i++) {
				deviceinfo = (CaptureDeviceInfo) devicelist.elementAt(i);
				devicename[i] = deviceinfo.getName();
				System.out.println("Device name is " + devicename[i]);
				Format sfmt[] = deviceinfo.getFormats();
				for (int i1 = 0; i1 < sfmt.length; i1++) {
					if (sfmt[i1] instanceof VideoFormat) {
						videodevice = CaptureDeviceManager.getDevice(devicename[i]);
						videoformat = sfmt[i1];
					}
					if (sfmt[i1] instanceof AudioFormat) {
						audiodevice = CaptureDeviceManager.getDevice(devicename[i]);
						audioformat = sfmt[i1];
					}
				}
			}
			MediaLocator m = videodevice.getLocator();
			System.out.println("Video device is " + m);
			MediaLocator m1 = audiodevice.getLocator();
			System.out.println("Audio device is " + m1);

			videolocator = videodevice.getLocator();
			System.out.println(videolocator);
			MediaLocator audiolocator = audiodevice.getLocator();
			System.out.println(audiolocator);

			datasource[0] = Manager.createDataSource(videolocator);
			datasource[1] = Manager.createDataSource(audiolocator);
			ds = Manager.createMergingDataSource(datasource);

			processor1 = Manager.createProcessor(ds);

			processor1.configure();
			while (processor1.getState() < processor1.Configured) {
				synchronized (state) {
					state.wait(100);
				}
			}

			processor1.setContentDescriptor(new FileTypeDescriptor(FileTypeDescriptor.MSVIDEO));
			TrackControl[] tcs1 = processor1.getTrackControls();
			for (int i = 0; i < tcs1.length; i++) {
				Format frmt = tcs1[i].getFormat();
				System.out.println("Format of track " + i + " is " + frmt);
				if (tcs1[i] instanceof VideoFormat) {
					tcs1[i].setFormat(new VideoFormat(VideoFormat.JPEG));
					System.out.println("The Format of track " + i + " is " + frmt);
				}
				if (tcs1[i] instanceof AudioFormat) {
					tcs1[i].setFormat(new AudioFormat(AudioFormat.LINEAR));
					System.out.println("The Format of track " + i + " is " + frmt);
				}
			}

			processor1.realize();
			while (processor1.getState() < processor1.Realized) {
				synchronized (state) {
					state.wait(100);
				}
			}

			processor1.prefetch();
			while (processor1.getState() < processor1.Prefetched) {
				synchronized (state) {
					state.wait(100);
				}
			}

			DataSource ds1 = processor1.getDataOutput();

			System.out.println("Data Source Content Type " + ds1.getContentType());
			System.out.println("Data Source Content Type " + ds.getContentType());

			DataSink datasink;
			MediaLocator out;
			String path1 = "file:///c:\\sam.avi";
			out = new MediaLocator(path1);
			datasink = Manager.createDataSink(ds1, out);
			datasink.open();
			datasink.start();
			ds1.start();
			processor1.start();
			/* processor1.close(); datasink.close(); ds1.stop(); System.exit(0); */
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

}

public class livesave {
	public static void main(String arg[]) {
		live1 l1 = new live1();
		l1.setVisible(true);
		l1.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});
	}
}
