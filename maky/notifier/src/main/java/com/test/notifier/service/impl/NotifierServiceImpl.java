package com.test.notifier.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.test.notifier.dao.TokenRepository;
import com.test.notifier.service.NotifierService;

@Service
public class NotifierServiceImpl implements NotifierService {

	private Logger logger = LoggerFactory.getLogger(NotifierServiceImpl.class);

	@Autowired
	private TokenRepository tokenRepository;

	@Scheduled(cron="* * * * * ?")
	public void testMethod(){
		System.out.println("Test");
	}
	
	// private String adminName;
	//
	// private Long adminUserId;
	//
	// @Required
	// public void setAdminName(String adminName) {
	// this.adminName = adminName;
	// }
	//
	// private Map<String, DeviceDescriptor> devices = new HashMap<String,
	// DeviceDescriptor>();
	//
	// @PostConstruct
	// public void init() {
	// //check for admin user
	// User user = getUser(adminName);
	// if (user == null) {
	// user = new User();
	// user.setName(adminName);
	// user = addUser(user);
	// }
	// adminUserId = user.getId();
	// }
	//
	// public List<AdTask> getSchedule(long startTime) {
	// return taskRepository.getTasksByStartTime(startTime);
	// }
	//
	// public List<Long> getScheduleIds(long startTime) {
	// return taskRepository.getTaskIdsFromStartTime(startTime);
	// }
	//
	// public AdTask addTask(AdTask task) {
	// return taskRepository.save(task);
	// }
	//
	// public AdTask getTask(Long taskId) {
	// return taskRepository.findOne(taskId);
	// }
	//
	// public AdTask removeTask(Long taskId) {
	// AdTask removedTask = taskRepository.findOne(taskId);
	// taskRepository.delete(removedTask);
	// return removedTask;
	// };
	//
	// @Override
	// public synchronized DeviceDescriptor getDevice(String id) {
	// return devices.get(id);
	// }
	//
	// @Override
	// public synchronized List<DeviceDescriptor> getDevices() {
	// return new ArrayList<DeviceDescriptor>(devices.values());
	// }
	//
	// @Override
	// public synchronized DeviceDescriptor registerDevice(DeviceDescriptor
	// device) {
	// if (devices.containsValue(device)) {
	// for (Map.Entry<String, DeviceDescriptor> entry : devices.entrySet()) {
	// if (device.equals(entry.getValue())) {
	// return entry.getValue();
	// }
	// }
	// logger.error("Error during registerinf device in devices");
	// } else {
	// String id = UUID.randomUUID().toString();
	// device.setId(id);
	// devices.put(id, device);
	// }
	// return device;
	// }
	//
	// @Override
	// public synchronized DeviceDescriptor unregisterDevice(DeviceDescriptor
	// device) {
	// return devices.remove(device.getId());
	// }
	//
	// @Override
	// public Network addNetwork(Network network) {
	// return networkRepository.save(network);
	// }
	//
	// @Override
	// public Network updateNetwork(Network network) {
	// return networkRepository.save(network);
	// }
	//
	// @Override
	// public Scenario addScenario(Scenario scenario) {
	// return scenarioRepository.save(scenario);
	// }
	//
	// @Override
	// public Scenario getScenario(Long id) {
	// return scenarioRepository.findOne(id);
	// }
	//
	// @Override
	// public User addUser(User user) {
	// return userRepository.save(user);
	// }
	//
	// @Override
	// public List<User> getUsers() {
	// return userRepository.findAll();
	// }
	//
	// @Override
	// public User getUser(String name) {
	// return userRepository.getUser(name);
	// }
	//
	// @Override
	// public List<Network> getNetworks() {
	// return networkRepository.findAll();
	// }
	//
	// @Override
	// public Network getNetwork(Long networkId) {
	// return networkRepository.findOne(networkId);
	// }
	//
	// @Override
	// public Campaign addCampaign(Campaign campaign) {
	// return campaignRepository.save(campaign);
	// }

}
