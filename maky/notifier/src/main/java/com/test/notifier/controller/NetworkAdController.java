package com.test.notifier.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.test.notifier.service.NotifierService;

@Controller
@Transactional
public class NetworkAdController {

	private static final Logger logger = LoggerFactory
			.getLogger(NetworkAdController.class);

	@Autowired
	private NotifierService networkAdService;

	private RestTemplate restTemplate;

	// public NetworkAdController() {
	// restTemplate = new RestTemplate();
	// List<HttpMessageConverter<?>> messageConverters = new
	// ArrayList<HttpMessageConverter<?>>();
	// messageConverters.add(new MappingJacksonHttpMessageConverter());
	// restTemplate.setMessageConverters(messageConverters);
	// }
	//
	// @RequestMapping(value = "/tasks", method = RequestMethod.GET)
	// @ResponseStatus(HttpStatus.OK)
	// public @ResponseBody
	// List<AdTask> getTasks(@RequestParam long startTime) {
	// return networkAdService.getSchedule(startTime);
	// }
	//
	// @RequestMapping(value = "/taskids", method = RequestMethod.GET)
	// @ResponseStatus(HttpStatus.OK)
	// public @ResponseBody
	// List<Long> getTaskIds(@RequestParam long startTime) {
	// return networkAdService.getScheduleIds(startTime);
	// }
	//
	// @RequestMapping(value = "/task", method = RequestMethod.PUT)
	// @ResponseStatus(HttpStatus.OK)
	// public @ResponseBody
	// AdTask addTask(@RequestBody AdTask task) {
	// // TODO: add checks for duplicate
	// AdTask result;
	// logger.debug("PUT task: " + task);
	// if (task.getStartTime() > System.currentTimeMillis() + 1000) {
	// result = networkAdService.addTask(task);
	// } else {
	// result = task;
	// }
	// return result;
	// }
	//
	// @RequestMapping(value = "/taskInterval", method = RequestMethod.PUT)
	// @ResponseStatus(HttpStatus.OK)
	// public @ResponseBody
	// ResponseStringStatus addTaskInterval(@RequestBody TaskViewModel
	// taskInterval) {
	// logger.debug("PUT taskInterval: " + taskInterval);
	// long period = (taskInterval.getEndTime() - taskInterval.getStartTime()) /
	// taskInterval.getNumber();
	// for (int i=0;i<taskInterval.getNumber();i++) {
	// AdTask task = new AdTask();
	// task.setContentLocation(taskInterval.getContentLocation());
	// task.setFullScreen(taskInterval.isFullScreen());
	// task.setStartTime(taskInterval.getStartTime() + period * i);
	// task.setType(taskInterval.getType());
	// task.setAppId(taskInterval.getAppId());
	// networkAdService.addTask(task);
	// }
	// ResponseStringStatus result = new ResponseStringStatus();
	// result.setStatus("Done");
	// return result;
	// }
	//
	// @RequestMapping(value = "/task", method = RequestMethod.DELETE)
	// @ResponseStatus(HttpStatus.OK)
	// public @ResponseBody
	// AdTask deleteTask(@RequestParam long id) {
	// logger.debug("DELETE taskId:" + id);
	// AdTask result = networkAdService.removeTask(id);
	// return result;
	// }
	//
	// @RequestMapping(value = "/task", method = RequestMethod.GET)
	// @ResponseStatus(HttpStatus.OK)
	// public @ResponseBody
	// AdTask getTask(@RequestParam long id) {
	// logger.debug("GET taskId: " + id);
	// AdTask result = networkAdService.getTask(id);
	// return result;
	// }
	//
	// @RequestMapping(value = "/device", method = RequestMethod.PUT)
	// @ResponseStatus(HttpStatus.OK)
	// public @ResponseBody
	// DeviceDescriptor registerDeviceService(@RequestBody DeviceDescriptor
	// device) {
	// logger.debug("PUT device");
	// DeviceDescriptor result = networkAdService.registerDevice(device);
	// return result;
	// }
	//
	// @RequestMapping(value = "/device/{id}", method = RequestMethod.GET)
	// @ResponseStatus(HttpStatus.OK)
	// public @ResponseBody
	// DeviceDescriptor getDevice(@PathVariable String id) {
	// return networkAdService.getDevice(id);
	// }
	//
	// @RequestMapping(value = "/device", method = RequestMethod.GET)
	// @ResponseStatus(HttpStatus.OK)
	// public @ResponseBody
	// List<DeviceDescriptor> getDevices() {
	// logger.debug("GET devices");
	// List<DeviceDescriptor> result = networkAdService.getDevices();
	// logger.info("Returns devices: " + result);
	// return result;
	// }
	//
	// @RequestMapping(value = "/device/{id}/presenter", method =
	// RequestMethod.PUT)
	// @ResponseStatus(HttpStatus.OK)
	// public @ResponseBody
	// ResponseStringStatus showPresenter(@PathVariable String id, @RequestBody
	// PresenterDescriptor descriptor) {
	// logger.info("POST actionDeviceService id=" + id);
	// DeviceDescriptor device = networkAdService.getDevice(id);
	// if (device == null) {
	// return new ResponseStringStatus("Device not found");
	// }
	// String url = "http://" + device.getHost() + ":" + device.getPort() + "/"
	// + device.getUrl() + "/presenter";
	// logger.info("POST actionDeviceService url=" + url);
	// restTemplate.postForObject(url, descriptor, Object.class);
	// return new ResponseStringStatus("Done");
	// }
	//
	// @RequestMapping(value = "/user", method = RequestMethod.GET)
	// @ResponseStatus(HttpStatus.OK)
	// public @ResponseBody
	// List<User> getUsers() {
	// logger.debug("GET users");
	// List<User> result = networkAdService.getUsers();
	// logger.info("Returns users: " + result);
	// return result;
	// }
	//
	// @RequestMapping(value = "/user", method = RequestMethod.PUT)
	// @ResponseStatus(HttpStatus.OK)
	// public @ResponseBody
	// User registerUser(@RequestBody UserViewModel userView) {
	// logger.debug("PUT user");
	// User user = new User();
	// user.setId(userView.getId());
	// user.setName(userView.getName());
	// Network network = networkAdService.getNetwork(userView.getNetworkId());
	// user.setNetwork(network);
	// User result = networkAdService.addUser(user);
	// return result;
	// }
	//
	// @RequestMapping(value = "/network", method = RequestMethod.PUT)
	// @ResponseStatus(HttpStatus.OK)
	// public @ResponseBody
	// Network addNetwork(@RequestBody NetworkViewModel networkView) {
	// logger.debug("PUT network");
	// Network network = new Network();
	// network.setId(networkView.getId());
	// network.setName(networkView.getName());
	// Network result = networkAdService.addNetwork(network);
	// logger.info("Added network: " + result);
	// return result;
	// }
	//
	// @RequestMapping(value = "/network", method = RequestMethod.GET)
	// @ResponseStatus(HttpStatus.OK)
	// public @ResponseBody
	// List<NetworkViewModel> getNetworks() {
	// logger.debug("GET networks");
	// List<Network> networks = networkAdService.getNetworks();
	// List<NetworkViewModel> result = new ArrayList<NetworkViewModel>();
	// for (Network network : networks) {
	// NetworkViewModel view = new NetworkViewModel();
	// view.setId(network.getId());
	// view.setName(network.getName());
	// result.add(view);
	// }
	// logger.info("Returns networks: " + result);
	// return result;
	// }
	//
	// @RequestMapping(value = "/campaign", method = RequestMethod.PUT)
	// @ResponseStatus(HttpStatus.OK)
	// public @ResponseBody
	// Campaign addCampaign(@RequestBody CampaignViewModel campaignView) {
	// logger.debug("PUT campaign");
	// Campaign campaign = new Campaign();
	// campaign.setId(campaignView.getId());
	// campaign.setDescription(campaignView.getDescription());
	// campaign.setEndDate(campaignView.getEndDate());
	// campaign.setStartDate(campaign.getStartDate());
	// campaign.setName(campaign.getName());
	// Network network =
	// networkAdService.getNetwork(campaignView.getNetworkId());
	// List<Campaign> campaigns = network.getCampaign();
	// campaigns.add(campaign);
	// List<Long> scenarioViews = campaignView.getScenarios();
	// if (scenarioViews != null) {
	// ArrayList<Scenario> scenarios = new
	// ArrayList<Scenario>(scenarioViews.size());
	// for(Long scenarioView: scenarioViews) {
	// Scenario scenario = networkAdService.getScenario(scenarioView);
	// scenarios.add(scenario);
	// }
	// campaign.setScenarios(scenarios);
	// }
	// Campaign result = networkAdService.addCampaign(campaign);
	// networkAdService.updateNetwork(network);
	// logger.info("Added campaign: " + result);
	// return result;
	// }

}
