package com.justnothing.xtchttplib;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.control.ListCell;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.control.SplitPane;
import javafx.geometry.Orientation;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import okhttp3.Response;
import okhttp3.ResponseBody;
import javafx.stage.Modality;
import javafx.scene.Scene;
import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class DeviceManagerController {
    private DeviceManager deviceManager;
    private ADBConnector adbConnector;
    private ObservableList<DeviceInfo> deviceList;
    private ObservableList<PackageInfo> packageList;
    private List<String> selectedParametersStringList = new ArrayList<>();
    private List<String> comboBoxItems = new ArrayList<>();
    private boolean noDeviceWarningShown = false;
    private Map<String, String> httpResultCodeMap;
    private Map<String, String> serverCodeResultMap;
    private static Logger logger = LoggerFactory.getLogger(DeviceManagerController.class);
    private ContextManager contextManager;

    @FXML
    private Label statusLabel;

    @FXML
    private ListView<DeviceInfo> deviceListView;

    @FXML
    private TextField deviceNameField;

    @FXML
    private TextField bindNumberField;

    @FXML
    private TextField watchIdField;

    @FXML
    private TextField macAddrField;

    @FXML
    private TextField chipIdField;

    @FXML
    private TextField modelField;

    @FXML
    private TextField versionField;

    @FXML
    private TextField timeZoneField;

    @FXML
    private TextField dataCenterField;

    @FXML
    private TextField greyField;

    @FXML
    private TextField tsField;

    @FXML
    private TextField aeField;

    @FXML
    private TextField encSwitchField;

    @FXML
    private TextField androidSdkField;

    @FXML
    private ComboBox<PackageInfo> packageNameCombo;

    @FXML
    private TextField versionNameField;

    @FXML
    private TextField versionCodeField;

    @FXML
    private TextField hardwareField;

    @FXML
    private TextField languageField;

    @FXML
    private TextField regionField;

    @FXML
    private TextField rsaPublicKeyField;

    @FXML
    private TextField selfRsaPublicKeyField;

    @FXML
    private TextField httpHeadParamField;

    @FXML
    private TextField innerModelField;

    @FXML
    private TextField serverInnerField;

    @FXML
    private TextField localeField;

    @FXML
    private TextField innerModelExField;

    @FXML
    private TextField watchPriModelField;

    @FXML
    private TextField buildTypeField;

    @FXML
    private TextField caremeOsVersionField;

    @FXML
    private TextField showModelField;

    @FXML
    private TextField buildReleaseField;

    @FXML
    private ComboBox<String> methodCombo;

    @FXML
    private TextField urlField;

    @FXML
    private TextArea requestBodyArea;
    
    @FXML
    private ComboBox<String> parameterCombo;
    
    @FXML
    private ListView<String> selectedParametersList;

    private final ObservableList<String> selectedParameters = FXCollections.observableArrayList();

    @FXML
    private CheckBox decryptResponseCheck;

    @FXML
    private TextArea responseArea;

    @FXML
    private Label responseStatusLabel;

    @FXML
    private TextArea logArea;

    @FXML
    private Label deviceCountLabel;

    @FXML
    private Label currentDeviceLabel;

    @FXML
    private ListView<HttpScript> scriptListView;

    @FXML
    private TableView<HttpRequest> requestTableView;

    @FXML
    private TableColumn<HttpRequest, Integer> indexColumn;

    @FXML
    private TableColumn<HttpRequest, String> methodColumn;

    @FXML
    private TableColumn<HttpRequest, String> urlColumn;

    @FXML
    private TableColumn<HttpRequest, String> packageColumn;

    @FXML
    private TableColumn<HttpRequest, String> bodyColumn;

    private ObservableList<HttpScript> scriptList;

    public String getStackTraceString(Throwable throwable) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        throwable.printStackTrace(pw);
        return sw.toString();
    }

    @FXML
    public void initialize() {
        deviceManager = new DeviceManager();
        adbConnector = new ADBConnector();

        deviceList = FXCollections.observableArrayList();
        deviceListView.setItems(deviceList);
        deviceListView.setCellFactory(param -> new DeviceListCell());

        scriptList = FXCollections.observableArrayList();
        scriptListView.setItems(scriptList);
        scriptListView.setCellFactory(param -> new TextFieldListCell<>());

        scriptListView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                updateRequestTableView(newVal);
            } else {
                requestTableView.setItems(FXCollections.observableArrayList());
            }
        });
        
        selectedParametersList.setItems(FXCollections.observableArrayList());
        comboBoxItems.clear();
        
        Platform.runLater(() -> {
            initializeParameterComboBox();
            parameterCombo.setOnAction(event -> onParameterSelected());
        });

        indexColumn.setCellValueFactory(param -> new javafx.beans.property.SimpleIntegerProperty(requestTableView.getItems().indexOf(param.getValue()) + 1).asObject());
        methodColumn.setCellValueFactory(param -> new javafx.beans.property.SimpleStringProperty(param.getValue().getMethod()));
        urlColumn.setCellValueFactory(param -> new javafx.beans.property.SimpleStringProperty(param.getValue().getUrl()));
        packageColumn.setCellValueFactory(param -> new javafx.beans.property.SimpleStringProperty(param.getValue().getPackageName()));
        bodyColumn.setCellValueFactory(param -> new javafx.beans.property.SimpleStringProperty(param.getValue().getBody() != null && param.getValue().getBody().length() > 30 ? param.getValue().getBody().substring(0, 30) + "..." : param.getValue().getBody()));

        requestTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                HttpRequest selectedRequest = requestTableView.getSelectionModel().getSelectedItem();
                if (selectedRequest != null) {
                    HttpScript selectedScript = scriptListView.getSelectionModel().getSelectedItem();
                    if (selectedScript != null) {
                        editRequestInDialog(null, selectedScript, requestTableView, selectedRequest);
                    }
                }
            }
        });

        methodCombo.setItems(FXCollections.observableArrayList("GET", "POST", "PUT", "DELETE", "PATCH"));
        methodCombo.getSelectionModel().select("POST");

        loadPackages();
        loadResultCodes();
        loadScripts();

        packageNameCombo.setOnAction(event -> {
            PackageInfo selected = packageNameCombo.getSelectionModel().getSelectedItem();
            if (selected != null) {
                versionNameField.setText(selected.getVersionName());
                versionCodeField.setText(selected.getVersionCode() != null ? selected.getVersionCode().toString() : "");
                updateParamsRealtime();
            }
        });

        loadDevices();
        setupEventHandlers();

        Slf4jAppender.initialize(message -> {
            Platform.runLater(() -> {
                logArea.appendText(message + "\n");
            });
        });

        logger.info("系统初始化完成");
    }

    private void setupEventHandlers() {
        deviceListView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                displayDeviceInfo(newVal);
            }
        });

        deviceListView.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                onSwitchDevice();
            }
        });

        logArea.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                logArea.clear();
            }
        });

        setupRealTimeParamUpdate();
    }

    private boolean isInitializing = false;

    private void setupRealTimeParamUpdate() {
        TextField[] paramFields = {
            greyField, tsField, aeField, encSwitchField, rsaPublicKeyField,
            selfRsaPublicKeyField, httpHeadParamField, watchIdField, bindNumberField,
            macAddrField, chipIdField, modelField, versionField, androidSdkField,
            hardwareField, languageField, regionField, timeZoneField, dataCenterField,
            innerModelField, serverInnerField, localeField, innerModelExField,
            watchPriModelField, buildTypeField, caremeOsVersionField,
            showModelField, buildReleaseField, versionNameField, versionCodeField, urlField
        };

        for (TextField field : paramFields) {
            if (field != null) {
                field.textProperty().addListener((obs, oldVal, newVal) -> {
                    if (!isInitializing && oldVal != null && !oldVal.equals(newVal)) {
                        updateParamsRealtime();
                    }
                });
            }
        }

        if (deviceNameField != null) {
            deviceNameField.textProperty().addListener((obs, oldVal, newVal) -> {
                if (!isInitializing && oldVal != null && !oldVal.equals(newVal)) {
                    DeviceInfo current = deviceManager.getCurrentDevice();
                    if (current != null) {
                        current.setDeviceName(newVal);
                        deviceManager.saveDevices();
                        updateCurrentDeviceLabel();
                        refreshDeviceListWithoutInfoUpdate();
                    }
                }
            });
        }
    }

    public ContextManager getContextManager() {
        return contextManager;
    }

    private void updateParamsRealtime() {
        logger.info("尝试更新当前参数");
        DeviceInfo current = deviceManager.getCurrentDevice();
        if (current == null) {
            return;
        }

        ContextManager context = current.getContext();
        if (context == null) {
            return;
        }

        try {
            context.setGrey(greyField.getText());
            String tsText = tsField.getText().trim();
            if (!tsText.isEmpty()) {
                try {
                    context.setTs(Integer.parseInt(tsText));
                } catch (NumberFormatException ignored) {
                }
            }
            context.setAe(aeField.getText());
            context.setEncSwitch(encSwitchField.getText());
            context.setRsaPublicKey(rsaPublicKeyField.getText());
            context.setHttpHeadParam(httpHeadParamField.getText());
            context.setWatchId(watchIdField.getText());
            context.setBindNumber(bindNumberField.getText());
            context.setMacAddr(macAddrField.getText());
            context.setChipId(chipIdField.getText());
            context.setWatchModel(modelField.getText());
            context.setSoftVersion(versionField.getText());
            String androidSdkText = androidSdkField.getText().trim();
            if (!androidSdkText.isEmpty()) {
                try {
                    context.setAndroidSdk(Integer.parseInt(androidSdkText));
                } catch (NumberFormatException ignored) {
                }
            }
            context.setHardware(hardwareField.getText());
            context.setLanguage(languageField.getText());
            context.setRegion(regionField.getText());
            context.setTimeZone(timeZoneField.getText());
            context.setDataCenterCode(dataCenterField.getText());
            context.setSelfRsaPublicKey(selfRsaPublicKeyField.getText());
            context.setInnerModel(innerModelField.getText());
            context.setServerInner(serverInnerField.getText());
            context.setLocale(localeField.getText());
            context.setInnerModelEx(innerModelExField.getText());
            context.setWatchPriModel(watchPriModelField.getText());
            context.setBuildType(buildTypeField.getText());
            context.setCaremeOsVersion(caremeOsVersionField.getText());
            context.setShowModel(showModelField.getText());
            context.setBuildRelease(buildReleaseField.getText());

            deviceManager.saveDevices();

        } catch (Exception ignored) {
        }
        contextManager = context;
    }

    private void refreshDeviceListWithoutInfoUpdate() {
        int selectedIndex = deviceListView.getSelectionModel().getSelectedIndex();
        deviceList.clear();
        deviceList.addAll(deviceManager.listDevices());
        if (selectedIndex >= 0 && selectedIndex < deviceList.size()) {
            deviceListView.getSelectionModel().select(selectedIndex);
        }
    }

    private void loadDevices() {
        deviceManager.loadDevices();
        deviceList.clear();
        deviceList.addAll(deviceManager.listDevices());
        updateDeviceCount();
        updateCurrentDeviceLabel();
        
        DeviceInfo current = deviceManager.getCurrentDevice();
        if (current != null) {
            deviceListView.getSelectionModel().select(current);
            displayDeviceInfo(current);
        }
    }

    private void loadResultCodes() {
        loadHttpResultCodes();
        loadServerCodeResultCodes();
    }

    private void loadPackages() {
        ObservableList<PackageInfo> packages = FXCollections.observableArrayList();
        try {
            // 首先尝试从data文件夹加载packages.json
            Path packagePath = DataPathManager.getPackagesJsonPath();
            if (!Files.exists(packagePath)) {
                // 如果data文件夹中的文件不存在，从资源文件创建默认配置
                logger.warn("packages.json不存在，从默认配置创建");
                try {
                    String defaultJsonStr = Files.readString(Paths.get(Objects.requireNonNull(getClass().getResource(DataPathManager.getDefaultPackagesResourcePath())).toURI()));
                    DataPathManager.ensureDataDirExists();
                    Files.writeString(packagePath, defaultJsonStr);
                    logger.info("已创建packages.json");
                } catch (Exception e) {
                    logger.warn("无法从默认配置创建packages.json", e);
                    // 如果创建失败，直接使用资源文件
                    String jsonStr = Files.readString(Paths.get(Objects.requireNonNull(getClass().getResource(DataPathManager.getDefaultPackagesResourcePath())).toURI()));
                    JSONArray jsonArray = new JSONArray(jsonStr);
                    
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject json = jsonArray.getJSONObject(i);
                        packages.add(PackageInfo.fromJson(json));
                    }
                    
                    packageNameCombo.setItems(packages);
                    this.packageList = packages;
                    return;
                }
            }
            
            // 从data文件夹加载packages.json
            String jsonStr = Files.readString(packagePath);
            JSONArray jsonArray = new JSONArray(jsonStr);
            
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject json = jsonArray.getJSONObject(i);
                packages.add(PackageInfo.fromJson(json));
            }
            
            packageNameCombo.setItems(packages);
            this.packageList = packages;
        } catch (Exception e) {
            logger.warn("无法加载packages.json", e);
        }
    }

    private PackageInfo findPackageInfo(String packageName) {
        if (packageList == null) {
            return null;
        }
        for (PackageInfo info : packageList) {
            if (info.getPackageName() != null && info.getPackageName().equals(packageName)) {
                return info;
            }
        }
        return null;
    }

    private void loadHttpResultCodes() {
        try {
            String jsonStr = Files.readString(Paths.get(Objects.requireNonNull(getClass().getResource("/http_result.json")).toURI()));
            JSONObject json = new JSONObject(jsonStr);
            httpResultCodeMap = new java.util.HashMap<>();
            
            for (String key : json.keySet()) {
                httpResultCodeMap.put(key, json.getString(key));
            }
        } catch (Exception e) {
            logger.warn("无法加载http_result.json", e);
            httpResultCodeMap = new java.util.HashMap<>();
        }
    }

    private void loadServerCodeResultCodes() {
        try {
            String jsonStr = Files.readString(Paths.get(getClass().getResource("/server_code_result.json").toURI()));
            JSONObject json = new JSONObject(jsonStr);
            serverCodeResultMap = new java.util.HashMap<>();
            
            for (String key : json.keySet()) {
                serverCodeResultMap.put(key, json.getString(key));
            }
        } catch (Exception e) {
            logger.warn("无法加载server_code_result.json", e);
            serverCodeResultMap = new java.util.HashMap<>();
        }
    }

    private void updateDeviceCount() {
        deviceCountLabel.setText("设备数: " + deviceList.size());
    }

    private void updateCurrentDeviceLabel() {
        DeviceInfo current = deviceManager.getCurrentDevice();
        if (current != null) {
            currentDeviceLabel.setText("当前设备: " + current.getDeviceName() + " (" + current.getBindNumber() + ")");
        } else {
            currentDeviceLabel.setText("当前设备: 无");
        }
    }

    private void displayDeviceInfo(DeviceInfo device) {
        ContextManager context = device.getContext();
        if (context == null) return;


        isInitializing = true;
        Platform.runLater(() -> {
            if (!deviceNameField.isFocused()) {
                deviceNameField.setText(device.getDeviceName());
            }
            bindNumberField.setText(context.getBindNumber());
            watchIdField.setText(context.getWatchId());
            macAddrField.setText(context.getMacAddr());
            chipIdField.setText(context.getChipId());
            modelField.setText(context.getWatchModel());
            versionField.setText(context.getSoftVersion());
            androidSdkField.setText(context.getAndroidSdk() != null ? context.getAndroidSdk().toString() : "");
            hardwareField.setText(context.getHardware());
            languageField.setText(context.getLanguage());
            regionField.setText(context.getRegion());
            timeZoneField.setText(context.getTimeZone());
            dataCenterField.setText(context.getDataCenterCode());
            
            greyField.setText(context.getGrey());
            tsField.setText(context.getTs() != null ? context.getTs().toString() : "");
            aeField.setText(context.getAe());
            encSwitchField.setText(context.getEncSwitch());
            rsaPublicKeyField.setText(context.getRsaPublicKey());
            selfRsaPublicKeyField.setText(context.getSelfRsaPublicKey());
            httpHeadParamField.setText(context.getHttpHeadParam());
            innerModelField.setText(context.getInnerModel());
            serverInnerField.setText(context.getServerInner());
            localeField.setText(context.getLocale());
            innerModelExField.setText(context.getInnerModelEx());
            watchPriModelField.setText(context.getWatchPriModel());
            buildTypeField.setText(context.getBuildType());
            caremeOsVersionField.setText(context.getCaremeOsVersion());
            showModelField.setText(context.getShowModel());
            buildReleaseField.setText(context.getBuildRelease());
            
            if (context.getPackageName() != null && !context.getPackageName().isEmpty()) {
                PackageInfo packageInfo = findPackageInfo(context.getPackageName());
                if (packageInfo != null) {
                    packageNameCombo.getSelectionModel().select(packageInfo);
                } else {
                    packageNameCombo.getSelectionModel().clearSelection();
                }
                versionNameField.setText(context.getPackageVersionName() != null ? context.getPackageVersionName() : "");
                versionCodeField.setText(context.getPackageVersionCode() != null ? context.getPackageVersionCode().toString() : "");
            } else {
                packageNameCombo.getSelectionModel().clearSelection();
                versionNameField.clear();
                versionCodeField.clear();
            }
            
            isInitializing = false;
        });
    }

    private void clearDeviceInfo() {
        Platform.runLater(() -> {
            deviceNameField.clear();
            bindNumberField.clear();
            watchIdField.clear();
            macAddrField.clear();
            chipIdField.clear();
            modelField.clear();
            versionField.clear();
            androidSdkField.clear();
            hardwareField.clear();
            languageField.clear();
            regionField.clear();
            timeZoneField.clear();
            dataCenterField.clear();
            greyField.clear();
            tsField.clear();
            aeField.clear();
            encSwitchField.clear();
            rsaPublicKeyField.clear();
            selfRsaPublicKeyField.clear();
            httpHeadParamField.clear();
            innerModelField.clear();
            serverInnerField.clear();
            localeField.clear();
            innerModelExField.clear();
            watchPriModelField.clear();
            buildTypeField.clear();
            caremeOsVersionField.clear();
            showModelField.clear();
            buildReleaseField.clear();
        });
    }

    @FXML
    private void onAddFromADB() {
        logger.info("开始从ADB添加设备...");
        
        if (!adbConnector.isADBAvailable()) {
            showAlert(Alert.AlertType.ERROR, "错误", "ADB不可用，请确保ADB已安装并在PATH中");
            logger.error("ADB不可用");
            return;
        }

        List<String> devices = adbConnector.detectDevices();
        if (devices.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "警告", "未检测到任何设备，请确保设备已连接并开启USB调试");
            logger.warn("未检测到设备");
            return;
        }

        ChoiceDialog<String> dialog = new ChoiceDialog<>(devices.get(0), devices);
        dialog.setTitle("选择设备");
        dialog.setHeaderText("检测到以下设备");
        dialog.setContentText("请选择要添加的设备:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            String deviceId = result.get();
            logger.info("正在从设备导出上下文, deviceId = " + deviceId);

            new Thread(() -> {
                try {
                    ContextManager context = adbConnector.exportContext(deviceId);
                    
                    Platform.runLater(() -> {
                        if (context == null) {
                            showAlert(Alert.AlertType.ERROR, "错误", "导出设备上下文失败");
                            logger.error("导出设备上下文失败, deviceId = " + deviceId);
                            return;
                        }

                        String bindNumber = context.getBindNumber();
                        if (bindNumber == null || bindNumber.isEmpty()) {
                            showAlert(Alert.AlertType.ERROR, "错误", "设备上下文中缺少bindNumber");
                            logger.error("导出设备上下文失败, deviceId = " + deviceId + ", 缺少bindNumber");
                            return;
                        }

                        if (deviceManager.hasDevice(bindNumber)) {
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("确认覆盖");
                            alert.setHeaderText("设备已存在: " + bindNumber);
                            alert.setContentText("是否覆盖现有设备？");

                            Optional<ButtonType> confirm = alert.showAndWait();
                            if (confirm.isPresent() && confirm.get() != ButtonType.OK) {
                                logger.info("已取消添加, deviceId = " + deviceId);
                                return;
                            }
                        }

                        DeviceInfo deviceInfo = new DeviceInfo(bindNumber, context);
                        deviceInfo.setAdbDeviceId(deviceId);

                        TextInputDialog nameDialog = new TextInputDialog(deviceInfo.getDeviceName());
                        nameDialog.setTitle("设备名称");
                        nameDialog.setHeaderText("设置设备名称");
                        nameDialog.setContentText("设备名称:");

                        Optional<String> nameResult = nameDialog.showAndWait();
                        if (nameResult.isPresent()) {
                            String deviceName = nameResult.get().trim();
                            if (!deviceName.isEmpty()) {
                                deviceInfo.setDeviceName(deviceName);
                            }
                        }

                        deviceManager.addDevice(deviceInfo);
                        deviceManager.switchDevice(bindNumber);

                        loadDevices();
                        updateCurrentDeviceLabel();
                        displayDeviceInfo(deviceInfo);

                        showAlert(Alert.AlertType.INFORMATION, "成功", "设备添加成功: " + bindNumber);
                        logger.info("设备添加成功, deviceId = " + deviceId + ", bindNumber = " + bindNumber);
                    });
                } catch (Exception e) {
                    Platform.runLater(() -> {
                        showAlert(Alert.AlertType.ERROR, "错误", "导出设备上下文时发生异常: " + e.getMessage());
                        logger.error("导出设备上下文时发生异常, deviceId = " + deviceId, e);
                        e.printStackTrace();
                    });
                }
            }).start();
        }
    }

    @FXML
    private void onAddFromJson() {
        logger.info("开始从JSON添加设备...");

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("从JSON添加设备");
        dialog.setHeaderText("请粘贴JSON配置");
        dialog.setContentText("JSON:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            String jsonStr = result.get().trim();
            if (jsonStr.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "错误", "JSON内容为空");
                logger.error("JSON内容为空");
                return;
            }


            // 在后台线程执行JSON解析操作
            new Thread(() -> {
                try {
                    ContextManager context = ContextManager.fromJson(jsonStr);
                    
                    Platform.runLater(() -> {
                        String bindNumber = context.getBindNumber();

                        if (bindNumber == null || bindNumber.isEmpty()) {
                            showAlert(Alert.AlertType.ERROR, "错误", "JSON中缺少bindNumber");
                            logger.error("JSON中缺少bindNumber");
                            return;
                        }

                        if (deviceManager.hasDevice(bindNumber)) {
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("确认覆盖");
                            alert.setHeaderText("设备已存在: " + bindNumber);
                            alert.setContentText("是否覆盖现有设备？");

                            Optional<ButtonType> confirm = alert.showAndWait();
                            if (confirm.isPresent() && confirm.get() != ButtonType.OK) {
                                logger.info("已取消添加, bindNumber = " + bindNumber);
                                return;
                            }
                        }

                        DeviceInfo deviceInfo = new DeviceInfo(bindNumber, context);
                        deviceManager.addDevice(deviceInfo);
                        deviceManager.switchDevice(bindNumber);

                        loadDevices();
                        updateCurrentDeviceLabel();
                        displayDeviceInfo(deviceInfo);

                        showAlert(Alert.AlertType.INFORMATION, "成功", "设备添加成功: " + bindNumber);
                        logger.info("设备添加成功, bindNumber = " + bindNumber);
                    });
                } catch (Exception e) {
                    Platform.runLater(() -> {
                        showAlert(Alert.AlertType.ERROR, "错误", "解析JSON失败: " + e.getMessage());
                        logger.error("解析JSON失败", e);
                        e.printStackTrace();
                    });
                }
            }).start();
        }
    }

    @FXML
    private void onSwitchDevice() {
        DeviceInfo selected = deviceListView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, "警告", "请先选择一个设备");
            return;
        }

        String bindNumber = selected.getBindNumber();
        deviceManager.switchDevice(bindNumber);
        updateCurrentDeviceLabel();
        displayDeviceInfo(selected);

        showAlert(Alert.AlertType.INFORMATION, "成功", "已切换到设备: " + selected.getDeviceName());
        logger.info("已切换到设备, bindNumber = " + bindNumber + ", deviceName = " + selected.getDeviceName());
    }

    @FXML
    private void onRenameDevice() {
        DeviceInfo selected = deviceListView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, "警告", "请先选择一个设备");
            return;
        }

        TextInputDialog dialog = new TextInputDialog(selected.getDeviceName());
        dialog.setTitle("重命名设备");
        dialog.setHeaderText("重命名设备: " + selected.getDeviceName());
        dialog.setContentText("新名称:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            String newName = result.get().trim();
            if (!newName.isEmpty()) {
                deviceManager.renameDevice(selected.getBindNumber(), newName);
                loadDevices();
                showAlert(Alert.AlertType.INFORMATION, "成功", "设备重命名成功");
                logger.info("设备重命名, bindNumber = " + selected.getBindNumber() + ", oldName = " + selected.getDeviceName() + ", newName = " + newName);
            }
        }
    }

    @FXML
    private void onRemoveDevice() {
        DeviceInfo selected = deviceListView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, "警告", "请先选择一个设备");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("确认删除");
        alert.setHeaderText("确定要删除设备: " + selected.getDeviceName() + " ?");
        alert.setContentText("此操作不可撤销！");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            deviceManager.removeDevice(selected.getBindNumber());
            loadDevices();
            updateCurrentDeviceLabel();
            clearDeviceInfo();

            showAlert(Alert.AlertType.INFORMATION, "成功", "设备删除成功");
            logger.info("设备删除, bindNumber = " + selected.getBindNumber() + ", deviceName = " + selected.getDeviceName());
        }
    }

    @FXML
    private void onExportDevice() {
        DeviceInfo selected = deviceListView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, "警告", "请先选择一个设备");
            return;
        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("导出设备配置");
        fileChooser.setInitialFileName(selected.getDeviceName() + ".json");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("JSON文件", "*.json")
        );

        File file = fileChooser.showSaveDialog(null);
        if (file != null) {
            try {
                String json = selected.getContext().toJson();
                Files.write(file.toPath(), json.getBytes(StandardCharsets.UTF_8));

                showAlert(Alert.AlertType.INFORMATION, "成功", "设备配置导出成功: " + file.getAbsolutePath());
                logger.info("将设备配置导出至" + file.getAbsolutePath());
            } catch (Exception e) {
                showAlert(Alert.AlertType.ERROR, "错误", "导出失败: " + e.getMessage());
                logger.error("导出失败", e);
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void onUpdateParams() {
        DeviceInfo current = deviceManager.getCurrentDevice();
        if (current == null) {
            showAlert(Alert.AlertType.WARNING, "警告", "没有当前设备");
            return;
        }

        ContextManager context = current.getContext();
        if (context == null) {
            showAlert(Alert.AlertType.ERROR, "错误", "设备上下文为空");
            return;
        }

        try {
            context.setGrey(greyField.getText());
            String tsText = tsField.getText().trim();
            if (!tsText.isEmpty()) {
                context.setTs(Integer.parseInt(tsText));
            }
            context.setAe(aeField.getText());
            context.setEncSwitch(encSwitchField.getText());
            context.setRsaPublicKey(rsaPublicKeyField.getText());
            context.setHttpHeadParam(httpHeadParamField.getText());
            context.setWatchId(watchIdField.getText());
            current.setDeviceName(deviceNameField.getText());
            context.setBindNumber(bindNumberField.getText());
            context.setMacAddr(macAddrField.getText());
            context.setChipId(chipIdField.getText());
            context.setWatchModel(modelField.getText());
            context.setSoftVersion(versionField.getText());
            String androidSdkText = androidSdkField.getText().trim();
            if (!androidSdkText.isEmpty()) {
                context.setAndroidSdk(Integer.parseInt(androidSdkText));
            }
            context.setHardware(hardwareField.getText());
            context.setLanguage(languageField.getText());
            context.setRegion(regionField.getText());
            context.setTimeZone(timeZoneField.getText());
            context.setDataCenterCode(dataCenterField.getText());
            context.setSelfRsaPublicKey(selfRsaPublicKeyField.getText());
            context.setInnerModel(innerModelField.getText());
            context.setServerInner(serverInnerField.getText());
            context.setLocale(localeField.getText());
            context.setInnerModelEx(innerModelExField.getText());
            context.setWatchPriModel(watchPriModelField.getText());
            context.setBuildType(buildTypeField.getText());
            context.setCaremeOsVersion(caremeOsVersionField.getText());
            context.setShowModel(showModelField.getText());
            context.setBuildRelease(buildReleaseField.getText());

            PackageInfo selectedPackage = packageNameCombo.getSelectionModel().getSelectedItem();
            if (selectedPackage != null) {
                context.setPackageName(selectedPackage.getPackageName());
                context.setPackageVersionName(versionNameField.getText());
                String versionCodeText = versionCodeField.getText().trim();
                if (!versionCodeText.isEmpty()) {
                    try {
                        context.setPackageVersionCode(Integer.parseInt(versionCodeText));
                    } catch (NumberFormatException e) {
                        logger.warn("VersionCode格式错误", e);
                    }
                }
            }

            deviceManager.updateDevice(current.getBindNumber(), context);

            showAlert(Alert.AlertType.INFORMATION, "成功", "参数更新成功");
            logger.info("参数更新成功");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "错误", "参数更新失败: " + e.getMessage());
            logger.error("参数更新失败", e);
            e.printStackTrace();
        }
    }

    @FXML
    private void onClearParams() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("确认清空");
        alert.setHeaderText("清空所有参数");
        alert.setContentText("确定要清空所有设备参数吗？此操作不可撤销。");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            clearDeviceInfo();
            showAlert(Alert.AlertType.INFORMATION, "成功", "参数已清空");
            logger.info("参数已清空");
        }
    }

    @FXML
    private void onResetParams() {
        DeviceInfo current = deviceManager.getCurrentDevice();
        if (current == null) {
            showAlert(Alert.AlertType.WARNING, "警告", "没有当前设备");
            return;
        }

        displayDeviceInfo(current);
        showAlert(Alert.AlertType.INFORMATION, "成功", "参数已重置");
        logger.info("参数已重置");
    }

    @FXML
    private void onSendRequest() {
        DeviceInfo current = deviceManager.getCurrentDevice();
        if (current == null) {
            if (!noDeviceWarningShown) {
                showAlert(Alert.AlertType.WARNING, "警告", "没有当前设备，建议先选择设备（详情见左侧列表，不会再次展示此提示）");
                noDeviceWarningShown = true;
            }
            return;
        }

        String method = methodCombo.getSelectionModel().getSelectedItem();
        String url = urlField.getText().trim();
        String body = requestBodyArea.getText().trim();

        PackageInfo selectedPackage = packageNameCombo.getSelectionModel().getSelectedItem();
        Integer versionCode = null;
        String versionName = null;
        
        if (selectedPackage != null) {
            versionName = versionNameField.getText();
            String versionCodeText = versionCodeField.getText().trim();
            if (!versionCodeText.isEmpty()) {
                try {
                    versionCode = Integer.parseInt(versionCodeText);
                } catch (NumberFormatException e) {
                    logger.warn("VersionCode格式错误", e);
                }
            }
            logger.info("设置应用信息: " + selectedPackage.getPackageName());
        }


        logger.info("发送HTTP请求: " + method + " " + url);
        logger.info("原始请求体: \n" + body);

        if (url.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "警告", "请输入URL");
            return;
        }

        try {
            HttpRequest request = new HttpRequest(method, url, body, selectedPackage != null ? selectedPackage.getPackageName() : "", versionCode, versionName);
            request.setParameters(selectedParametersStringList);
            Response response = request.execute(current);

            String statusCode = String.valueOf(response.code());
            String httpStatusText = httpResultCodeMap.getOrDefault(statusCode, statusCode);
            String responseStr = "";

            ResponseBody responseBody = response.body();
            if (responseBody != null) {
                responseStr = responseBody.string();
            }

            String serverCodeText = "";
            try {
                JSONObject jsonResponse = new JSONObject(responseStr);
                if (jsonResponse.has("code")) {
                    String serverCode = String.valueOf(jsonResponse.get("code"));
                    serverCodeText = " | 服务器: " + serverCode + " " + serverCodeResultMap.getOrDefault(serverCode, serverCode);
                }
            } catch (Exception ignored) {
            }

            String finalStatusText = httpStatusText + serverCodeText;
            String finalResponseStr = responseStr;
            Platform.runLater(() -> {
                responseStatusLabel.setText("状态: " + finalStatusText);
                responseArea.setText(finalResponseStr);
            });

            logger.info("请求完成，状态码: " + response.code());

        } catch (Exception e) {
            String error = "请求失败: " + e.getMessage();
            Platform.runLater(() -> {
                responseArea.setText(error);
            });
            logger.error("错误: " + error, e);
        }
    }

    @FXML
    private void onClearLogs() {
        logArea.clear();
        logger.info("日志已清空");
    }

    @FXML
    private void onCopyResponse() {
        String content = responseArea.getText();
        if (content != null && !content.isEmpty()) {
            javafx.scene.input.Clipboard clipboard = javafx.scene.input.Clipboard.getSystemClipboard();
            javafx.scene.input.ClipboardContent clipboardContent = new javafx.scene.input.ClipboardContent();
            clipboardContent.putString(content);
            clipboard.setContent(clipboardContent);
            logger.info("响应内容已复制到剪贴板");
        }
    }

    private void loadScripts() {
        try {
            Path scriptPath = DataPathManager.getHttpScriptsJsonPath();
            if (!Files.exists(scriptPath)) {
                logger.info("http_scripts.json 不存在，从默认配置创建");
                try {
                    String defaultJsonStr = new String(Files.readAllBytes(Paths.get(getClass().getResource(DataPathManager.getDefaultScriptsResourcePath()).toURI())), java.nio.charset.StandardCharsets.UTF_8);
                    DataPathManager.ensureDataDirExists();
                    Files.write(scriptPath, defaultJsonStr.getBytes(java.nio.charset.StandardCharsets.UTF_8));
                    logger.info("已创建 http_scripts.json");
                } catch (Exception e) {
                    logger.warn("无法从default_scripts.json创建文件", e);
                    return;
                }
            }
            String jsonStr = new String(Files.readAllBytes(scriptPath), java.nio.charset.StandardCharsets.UTF_8);
            JSONArray jsonArray = new JSONArray(jsonStr);
            scriptList.clear();
            
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject json = jsonArray.getJSONObject(i);
                scriptList.add(HttpScript.fromJson(json));
            }
        } catch (Exception e) {
            logger.warn("无法加载http_scripts.json", e);
        }
    }

    private void saveScripts() {
        try {
            JSONArray jsonArray = new JSONArray();
            for (HttpScript script : scriptList) {
                jsonArray.put(script.toJson());
            }
            
            String jsonStr = jsonArray.toString(2);
            DataPathManager.ensureDataDirExists();
            Files.write(DataPathManager.getHttpScriptsJsonPath(), jsonStr.getBytes(java.nio.charset.StandardCharsets.UTF_8));
        } catch (Exception e) {
            logger.error("错误: 保存脚本失败", e);
        }
    }

    private void updateRequestTableView(HttpScript script) {
        logger.info("更新请求列表，脚本名: " + script.getName());
        requestTableView.setItems(FXCollections.observableArrayList(script.getRequests()));
        requestTableView.refresh();
    }
    
    /**
     * 初始化参数选择器
     */
    private void initializeParameterComboBox() {
        if (parameterCombo == null) return;
        
        ParameterManager paramManager = new ParameterManager();
        List<Parameter> parameters = paramManager.getAvailableParameters();
        
        parameterCombo.getItems().clear();
        parameterCombo.getItems().add("选择参数");
        comboBoxItems.clear();
        
        for (Parameter param : parameters) {
            String displayText = param.getDisplayName() + " (" + param.getName() + ")";
            parameterCombo.getItems().add(displayText);
            comboBoxItems.add(param.getName());
        }
        
        parameterCombo.getSelectionModel().select(0);
    }
    

    private void onParameterSelected() {
        logger.info("添加参数");
        int selected = parameterCombo.getSelectionModel().getSelectedIndex();
        if (selected == 0) return; // 忽略选择第一个项
        
        String selectedParam = comboBoxItems.get(selected - 1);

        
        logger.info("选择的参数: " + selectedParam);
 
        int parameterIndex = selectedParametersStringList.size() + 1;
        String parameterDisplay = selectedParam + " (%" + parameterIndex + "$s)";
        selectedParameters.add(parameterDisplay);
        selectedParametersStringList.add(selectedParam);
        selectedParametersList.setItems(selectedParameters);
        
        // 重置选择器
        parameterCombo.getSelectionModel().select(0);
        
        logger.info("已添加参数: " + selectedParam + " (在请求体格式化中的位置: %" + parameterIndex + "$s)");
    }
    
    /**
     * 清空参数列表
     */
    @FXML
    private void onClearParameterList() {
        selectedParameters.clear();
        selectedParametersStringList.clear();
        selectedParametersList.setItems(selectedParameters);
        logger.info("已清空参数列表");
    }
    
    /**
     * 生成格式化模板
     */
    @FXML
    private void onGenerateTemplate() {
        if (selectedParametersStringList.isEmpty()) {
            logger.warn("没有添加参数到列表");
            showAlert(Alert.AlertType.WARNING, "警告", "请先添加参数到列表");
            return;
        }
        
        // 构建格式化字符串
        StringBuilder templateBuilder = new StringBuilder();
        List<String> paramNames = new ArrayList<>();
        
        templateBuilder.append("{\n");
        templateBuilder.append("  \"request\": {\n");
        
        for (int i = 0; i < selectedParametersStringList.size(); i++) {
            
            templateBuilder.append("    \"").append(selectedParametersStringList.get(i)).append("\": \"%")
                          .append(i + 1).append("$s\"");
            
            if (i < selectedParametersStringList.size() - 1) {
                templateBuilder.append(",\n");
            } else {
                templateBuilder.append("\n");
            }
            
            paramNames.add(selectedParametersStringList.get(i));
        }
        
        templateBuilder.append("  }\n");
        templateBuilder.append("}");
        
        // 插入到请求体
        if (requestBodyArea != null) {
            requestBodyArea.setText(templateBuilder.toString());
            logger.info("已生成格式化模板，使用位置参数: " + paramNames);
        }
    }
    
    /**
     * 插入参数到请求体
     */
    @FXML
    private void onInsertParameter() {
        if (parameterCombo == null || requestBodyArea == null) return;
        
        int index = parameterCombo.getSelectionModel().getSelectedIndex();
        if (index == 0) {
            logger.warn("未选择有效参数");
            showAlert(Alert.AlertType.WARNING, "警告", "请选择一个有效参数");
            return;
        }

        String selected = comboBoxItems.get(index - 1);
        int parameterIndex = selectedParameters.size() + 1;
        logger.info("选择的参数: " + selected + ", 在提供的参数列表中的索引: " + (index - 1) + ", 当前在已选择的参数列表中的位置: " + parameterIndex);
        
        // 插入位置参数
        String parameterText = "%" + parameterIndex + "$s";
        int caretPosition = requestBodyArea.getCaretPosition();
        requestBodyArea.insertText(caretPosition, parameterText);
        requestBodyArea.positionCaret(caretPosition + parameterText.length());
        
        logger.info("已插入参数: " + selected + " (位置: " + parameterText + ")");
    }
    

    @FXML
    private void onNewScript() {
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("新建脚本");
        dialog.setHeaderText("输入脚本名称");
        dialog.setContentText("脚本名称:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent() && !result.get().trim().isEmpty()) {
            String scriptName = result.get().trim();
            HttpScript script = new HttpScript(scriptName);
            scriptList.add(script);
            saveScripts();
            logger.info("脚本创建成功: " + scriptName);
        }
    }

    @FXML
    private void onAddRequest() {
        HttpScript selectedScript = scriptListView.getSelectionModel().getSelectedItem();
        if (selectedScript == null) {
            showAlert(Alert.AlertType.WARNING, "警告", "请先选择一个脚本");
            return;
        }

        Dialog<HttpRequest> dialog = new Dialog<>();
        dialog.setTitle("添加请求");
        dialog.setHeaderText("输入HTTP请求信息");

        ButtonType addButtonType = new ButtonType("添加", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));

        ComboBox<String> methodCombo = new ComboBox<>();
        methodCombo.getItems().addAll("GET", "POST", "PUT", "DELETE", "PATCH");
        methodCombo.setValue("POST");

        TextField urlField = new TextField();
        urlField.setPromptText("URL");

        ComboBox<PackageInfo> packageCombo = new ComboBox<>();
        packageCombo.setItems(packageNameCombo.getItems());
        packageCombo.setCellFactory(param -> new ListCell<PackageInfo>() {
            @Override
            protected void updateItem(PackageInfo item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.toString());
                }
            }
        });
        packageCombo.setButtonCell(packageCombo.getCellFactory().call(null));

        TextField versionCodeField = new TextField();
        versionCodeField.setPromptText("版本号");

        TextField versionNameField = new TextField();
        versionNameField.setPromptText("版本名称");

        packageCombo.setOnAction(event -> {
            PackageInfo selected = packageCombo.getSelectionModel().getSelectedItem();
            if (selected != null) {
                if (selected.getVersionCode() != null) {
                    versionCodeField.setText(selected.getVersionCode().toString());
                } else {
                    versionCodeField.clear();
                }
                if (selected.getVersionName() != null) {
                    versionNameField.setText(selected.getVersionName());
                } else {
                    versionNameField.clear();
                }
            }
        });

        VBox mainContainer = new VBox(5);
        mainContainer.setPadding(new Insets(5));

        grid.add(new Label("方法:"), 0, 0);
        grid.add(methodCombo, 1, 0);
        grid.add(new Label("URL:"), 0, 1);
        grid.add(urlField, 1, 1);
        grid.add(new Label("应用:"), 0, 2);
        grid.add(packageCombo, 1, 2);
        grid.add(new Label("版本号:"), 0, 3);
        grid.add(versionCodeField, 1, 3);
        grid.add(new Label("版本名称:"), 0, 4);
        grid.add(versionNameField, 1, 4);

        // 参数格式化区域
        HBox parameterContainer = new HBox(10);
        parameterContainer.setPadding(new Insets(5, 0, 5, 0));
        
        VBox parameterBox = new VBox(8);
        parameterBox.setStyle("-fx-border-color: #e0e0e0; -fx-border-width: 1; -fx-border-radius: 4; -fx-padding: 5;");
        parameterBox.setPrefWidth(250);
        
        Label parameterLabel = new Label("参数格式化");
        parameterLabel.setStyle("-fx-font-weight: bold;");
        
        ComboBox<String> scriptParamCombo = new ComboBox<>();
        List<String> scriptComboItems = new ArrayList<>();
        scriptParamCombo.setPromptText("选择参数");
        scriptParamCombo.setPrefWidth(240);
        
        // 初始化参数选择器
        ParameterManager paramManager = new ParameterManager();
        List<Parameter> parameters = paramManager.getAvailableParameters();
        scriptParamCombo.getItems().add("选择参数");
        for (Parameter param : parameters) {
            String displayText = param.getDisplayName() + " (" + param.getName() + ")";
            scriptParamCombo.getItems().add(displayText);
            scriptComboItems.add(param.getName());
        }
        scriptParamCombo.getSelectionModel().select(0);
        
        Label selectedParamLabel = new Label("已选参数:");
        selectedParamLabel.setStyle("-fx-font-weight: bold;");
        
        ListView<String> scriptSelectedParams = new ListView<>();
        scriptSelectedParams.setPrefHeight(100);
        ObservableList<String> scriptSelectedParameters = FXCollections.observableArrayList();
        scriptSelectedParams.setItems(scriptSelectedParameters);
        
        HBox parameterButtons = new HBox(2);
        Button clearButton = new Button("清空列表");
        Button generateButton = new Button("生成模板");
        parameterButtons.getChildren().addAll(clearButton, generateButton);
        
        parameterBox.getChildren().addAll(parameterLabel, scriptParamCombo, selectedParamLabel, scriptSelectedParams, parameterButtons);
        
        Label bodyLabel = new Label("请求体 (可选):");
        TextArea bodyArea = new TextArea();
        List<String> scriptSelectedParametersStringList = new ArrayList<>();
        bodyArea.setPromptText("请求体");
        bodyArea.setPrefRowCount(15);
        bodyArea.setMinHeight(300);
        bodyArea.setPrefHeight(300);
        bodyArea.setMaxHeight(300);
        bodyArea.getStyleClass().add("request-body-area");
        bodyArea.setWrapText(true);
        
        // 参数选择事件
        scriptParamCombo.setOnAction(event -> {
            int index = scriptParamCombo.getSelectionModel().getSelectedIndex();
            if (index == 0) {
                logger.warn("没有选择参数");
                showAlert(Alert.AlertType.WARNING, "警告", "请先选择参数");
                return;
            }
            String selected = scriptComboItems.get(index - 1);
            int parameterIndex = scriptSelectedParameters.size() + 1;
            logger.info("选择的参数: " + selected + ", 在提供的参数列表中的索引: " + (index - 1) + ", 当前在已选择的参数列表中的位置: " + parameterIndex);
            String parameterDisplay = selected + " (%" + parameterIndex + "$s)";
            scriptSelectedParameters.add(parameterDisplay);
            scriptSelectedParametersStringList.add(parameterDisplay);

            scriptParamCombo.getSelectionModel().select(0);
        });
        
        clearButton.setOnAction(event -> {
            scriptSelectedParameters.clear();
            scriptSelectedParametersStringList.clear();
        });
        
        generateButton.setOnAction(event -> {
            if (scriptSelectedParameters.isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "警告", "请先添加参数到列表");
                return;
            }
            
            StringBuilder templateBuilder = new StringBuilder();
            templateBuilder.append("{\n");
            templateBuilder.append("  \"request\": {\n");
            
            for (int i = 0; i < scriptSelectedParametersStringList.size(); i++) {
                String paramName = scriptSelectedParametersStringList.get(i);
                templateBuilder.append("    \"").append(paramName).append("\": \"%")
                              .append(i + 1).append("$s\"");
                
                if (i < scriptSelectedParametersStringList.size() - 1) {
                    templateBuilder.append(",\n");
                } else {
                    templateBuilder.append("\n");
                }
            }
            
            templateBuilder.append("  }\n");
            templateBuilder.append("}");
            
            bodyArea.setText(templateBuilder.toString());
        });
        
        // 布局容器
        parameterContainer.getChildren().addAll(bodyArea, parameterBox);
        mainContainer.getChildren().addAll(grid, bodyLabel, parameterContainer);

        javafx.scene.Node addButton = dialog.getDialogPane().lookupButton(addButtonType);
        addButton.setDisable(true);
        addButton.setStyle("-fx-min-height: 32; -fx-pref-height: 32;");

        urlField.textProperty().addListener((observable, oldValue, newValue) -> {
            addButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(mainContainer);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType) {
                PackageInfo selectedPackage = packageCombo.getSelectionModel().getSelectedItem();
                String packageName = selectedPackage != null ? selectedPackage.getPackageName() : "";
                
                Integer versionCode = null;
                try {
                    String versionCodeText = versionCodeField.getText().trim();
                    if (!versionCodeText.isEmpty()) {
                        versionCode = Integer.parseInt(versionCodeText);
                    }
                } catch (NumberFormatException e) {
                }
                
                String versionName = versionNameField.getText().trim();
                
                HttpRequest request = new HttpRequest(
                    methodCombo.getValue(),
                    urlField.getText().trim(),
                    bodyArea.getText().trim(),
                    packageName,
                    versionCode,
                    versionName
                );
               
                request.setParameters(scriptSelectedParametersStringList);
                logger.info("保存的请求信息: " + request.toString());
                
                return request;
            }
            return null;
        });

        Optional<HttpRequest> result = dialog.showAndWait();
        result.ifPresent(request -> {
            selectedScript.addRequest(request);
            updateRequestTableView(selectedScript);
            saveScripts();
            logger.info("请求添加成功: " + request.getMethod() + " " + request.getUrl());
        });
    }

    @FXML
    private void onDeleteRequest() {
        HttpScript selectedScript = scriptListView.getSelectionModel().getSelectedItem();
        if (selectedScript == null) {
            showAlert(Alert.AlertType.WARNING, "警告", "请先选择一个脚本");
            return;
        }

        HttpRequest selectedRequest = requestTableView.getSelectionModel().getSelectedItem();
        if (selectedRequest == null) {
            showAlert(Alert.AlertType.WARNING, "警告", "请先选择一个请求");
            return;
        }

        int index = selectedScript.getRequests().indexOf(selectedRequest);
        selectedScript.removeRequest(index);
        saveScripts();
        updateRequestTableView(selectedScript);
        logger.info("请求已删除");
    }

    @FXML
    private void onAddPackageConfig() {
        Dialog<PackageInfo> dialog = new Dialog<>();
        dialog.setTitle("添加应用配置");
        dialog.setHeaderText("输入应用信息");

        ButtonType addButtonType = new ButtonType("添加", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField packageNameField = new TextField();
        packageNameField.setPromptText("应用包名");

        TextField versionCodeField = new TextField();
        versionCodeField.setPromptText("版本号");

        TextField versionNameField = new TextField();
        versionNameField.setPromptText("版本名称");

        TextField displayNameField = new TextField();
        displayNameField.setPromptText("显示名称");

        grid.add(new Label("应用包名:"), 0, 0);
        grid.add(packageNameField, 1, 0);
        grid.add(new Label("版本号:"), 0, 1);
        grid.add(versionCodeField, 1, 1);
        grid.add(new Label("版本名称:"), 0, 2);
        grid.add(versionNameField, 1, 2);
        grid.add(new Label("显示名称:"), 0, 3);
        grid.add(displayNameField, 1, 3);

        javafx.scene.Node addButton = dialog.getDialogPane().lookupButton(addButtonType);
        addButton.setDisable(true);
        addButton.setStyle("-fx-min-height: 32; -fx-pref-height: 32;");

        packageNameField.textProperty().addListener((observable, oldValue, newValue) -> {
            addButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType) {
                Integer versionCode = null;
                try {
                    versionCode = Integer.parseInt(versionCodeField.getText().trim());
                } catch (NumberFormatException e) {
                    showAlert(Alert.AlertType.ERROR, "错误", "VersionCode必须是数字");
                    return null;
                }
                return new PackageInfo(
                    packageNameField.getText().trim(),
                    versionCode,
                    versionNameField.getText().trim(),
                    displayNameField.getText().trim()
                );
            }
            return null;
        });

        Optional<PackageInfo> result = dialog.showAndWait();
        result.ifPresent(packageInfo -> {
            try {
                Path packagePath = DataPathManager.getPackagesJsonPath();
                JSONArray jsonArray;
                if (Files.exists(packagePath)) {
                    String jsonStr = new String(Files.readAllBytes(packagePath), java.nio.charset.StandardCharsets.UTF_8);
                    jsonArray = new JSONArray(jsonStr);
                } else {
                    jsonArray = new JSONArray();
                }
                
                JSONObject json = new JSONObject();
                json.put("packageName", packageInfo.getPackageName());
                json.put("versionCode", packageInfo.getVersionCode());
                json.put("versionName", packageInfo.getVersionName());
                json.put("packageDisplayName", packageInfo.getPackageDisplayName());
                
                jsonArray.put(json);
                
                DataPathManager.ensureDataDirExists();
                Files.write(packagePath, jsonArray.toString(2).getBytes(java.nio.charset.StandardCharsets.UTF_8));
                
                loadPackages();
                logger.info("应用配置添加成功: " + packageInfo.getPackageName());
            } catch (Exception e) {
                showAlert(Alert.AlertType.ERROR, "错误", "保存应用配置失败: " + e.getMessage());
                logger.error("保存应用配置失败", e);
            }
        });
    }

    @FXML
    private void onDeleteScript() {
        HttpScript selected = scriptListView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, "警告", "请先选择一个脚本");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("确认删除");
        alert.setHeaderText("删除脚本");
        alert.setContentText("确定要删除脚本 \"" + selected.getName() + "\" 吗？");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            scriptList.remove(selected);
            saveScripts();
            logger.info("脚本已删除: " + selected.getName());
        }
    }

    @FXML
    private void onExecuteScript() {
        DeviceInfo current = deviceManager.getCurrentDevice();
        if (current == null) {
            if (!noDeviceWarningShown) {
                showAlert(Alert.AlertType.WARNING, "警告", "没有当前设备，建议先选择设备（详情见左侧列表，不会再次展示此提示）");
                noDeviceWarningShown = true;
            }
            return;
        }

        HttpScript selected = scriptListView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, "警告", "请先选择一个脚本");
            return;
        }

        if (selected.getRequests().isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "警告", "该脚本没有请求");
            return;
        }

        createScriptExecutionDialog(selected, current);
    }

    private void createScriptExecutionDialog(HttpScript script, DeviceInfo device) {
        Stage dialog = new Stage();
        dialog.setTitle("执行脚本: " + script.getName());
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setResizable(true);
        dialog.setWidth(900);
        dialog.setHeight(700);

        VBox root = new VBox(10);
        root.setPadding(new Insets(15));

        Label titleLabel = new Label("脚本: " + script.getName() + " (共 " + script.getRequests().size() + " 个请求)");
        titleLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        Label statusLabel = new Label("准备执行...");
        statusLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #666666;");

        SplitPane splitPane = new SplitPane();
        splitPane.setOrientation(Orientation.VERTICAL);

        VBox topPane = new VBox(10);
        topPane.setPadding(new Insets(10));

        Label requestListLabel = new Label("请求列表:");
        requestListLabel.setStyle("-fx-font-weight: bold;");

        ListView<HttpRequest> requestListView = new ListView<>();
        requestListView.setItems(FXCollections.observableArrayList(script.getRequests()));
        requestListView.setCellFactory(param -> new ListCell<HttpRequest>() {
            @Override
            protected void updateItem(HttpRequest request, boolean empty) {
                super.updateItem(request, empty);
                if (empty || request == null) {
                    setText(null);
                } else {
                    String bodyPreview = request.getBody() != null && request.getBody().length() > 30 
                        ? request.getBody().substring(0, 30) + "..." 
                        : request.getBody();
                    
                    String paramInfo = "";
                    if (request.hasParameters()) {
                        paramInfo = " [" + request.getParameters().size() + "个参数]";
                    }
                    
                    setText(request.getMethod() + " " + request.getUrl() + (bodyPreview != null && !bodyPreview.isEmpty() ? " - " + bodyPreview : "") + paramInfo);
                }
            }
        });


        topPane.getChildren().addAll(requestListLabel, requestListView);

        VBox bottomPane = new VBox(10);
        bottomPane.setPadding(new Insets(10));

        Label logLabel = new Label("执行日志:");
        logLabel.setStyle("-fx-font-weight: bold;");

        TextArea logArea = new TextArea();
        logArea.setEditable(false);
        logArea.setStyle("-fx-font-family: 'Consolas', 'Monaco', monospace; -fx-font-size: 12px;");
        logArea.setWrapText(true);

        Label responseLabel = new Label("响应内容:");
        responseLabel.setStyle("-fx-font-weight: bold;");

        TextArea responseArea = new TextArea();
        responseArea.setEditable(false);
        responseArea.setStyle("-fx-font-family: 'Consolas', 'Monaco', monospace; -fx-font-size: 12px;");
        responseArea.setWrapText(true);
        responseArea.setPrefHeight(200);

        bottomPane.getChildren().addAll(logLabel, logArea, responseLabel, responseArea);

        splitPane.getItems().addAll(topPane, bottomPane);
        splitPane.setDividerPositions(0.4);

        Button closeButton = new Button("关闭");
        closeButton.setDisable(true);
        closeButton.setOnAction(e -> dialog.close());

        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER_RIGHT);
        buttonBox.getChildren().add(closeButton);

        root.getChildren().addAll(titleLabel, statusLabel, splitPane, buttonBox);

        Scene scene = new Scene(root);
        dialog.setScene(scene);

        dialog.show();

        new Thread(() -> {
            boolean[] shouldContinue = {true};

            for (int i = 0; i < script.getRequests().size() && shouldContinue[0]; i++) {
                HttpRequest request = script.getRequests().get(i);
                final int requestIndex = i + 1;
                final int totalRequests = script.getRequests().size();

                Platform.runLater(() -> {
                    statusLabel.setText("\n\n执行中: " + requestIndex + "/" + totalRequests + " - " + request.getMethod() + " " + request.getUrl());
                    logArea.appendText("\n[" + requestIndex + "/" + totalRequests + "] " + request.getMethod() + " " + request.getUrl() + "\n");
                    logArea.appendText("\n格式化后的请求体:\n" + request.getFormattedBody(device) + "\n\n");
                });

                try {
                    Response response = request.execute(device);
                    Platform.runLater(() -> {
                        if (request.getPackageName() != null && !request.getPackageName().isEmpty()) {
                            logger.info("设置应用包名: " + request.getPackageName() + 
                                (request.getVersionCode() != null ? " (版本号: " + request.getVersionCode() + ")" : "") +
                                (request.getVersionName() != null && !request.getVersionName().isEmpty() ? " (版本名称: " + request.getVersionName() + ")" : "") + "\n");
                        }
                        if (request.hasParameters()) {
                            logger.info("请求体包含" + request.getParameters().size() + "个参数\n");
                        }
                    });
  
                    String statusCode = String.valueOf(response.code());
                    String httpStatusText = httpResultCodeMap.getOrDefault(statusCode, statusCode);
                    String responseStr = "";

                    ResponseBody responseBody = response.body();
                    if (responseBody != null) {
                        responseStr = responseBody.string();
                    }

                    String serverCodeText = "";
                    boolean success = true;
                    try {
                        JSONObject jsonResponse = new JSONObject(responseStr);
                        if (jsonResponse.has("code")) {
                            String serverCode = String.valueOf(jsonResponse.get("code"));
                            serverCodeText = " | 服务器: " + serverCode + " " + serverCodeResultMap.getOrDefault(serverCode, serverCode);
                            if (!serverCode.equals("000001")) {
                                success = false;
                            }
                        }
                    } catch (Exception e) {
                    }

                    String finalStatusText = httpStatusText + serverCodeText;
                    String finalResponseStr = responseStr;
                    final String finalHttpStatusText = httpStatusText;
                    final String finalServerCodeText = serverCodeText;

                    Platform.runLater(() -> {
                        logger.info("状态: " + finalStatusText + "\n");
                        logArea.appendText("状态: " + finalStatusText + "\n");
                        responseArea.setText(finalResponseStr);
                    });

                    if (!success) {
                        Platform.runLater(() -> {
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("执行警告");
                            alert.setHeaderText("请求 " + requestIndex + " 执行遇到问题");
                            alert.setContentText("HTTP状态: " + finalHttpStatusText + "\n服务器状态: " + finalServerCodeText + "\n\n是否继续执行下一个请求？");

                            ButtonType continueButton = new ButtonType("继续", ButtonBar.ButtonData.YES);
                            ButtonType stopButton = new ButtonType("停止", ButtonBar.ButtonData.NO);
                            alert.getButtonTypes().setAll(continueButton, stopButton);

                            Optional<ButtonType> result = alert.showAndWait();
                            if (!result.isPresent() || result.get() == stopButton) {
                                shouldContinue[0] = false;
                                logger.info("脚本执行已停止\n");
                                logArea.appendText("脚本执行已停止\n");
                            }
                        });
                    }

                } catch (Exception e) {
                    String error = "请求失败: " + e.getMessage();
                    final int errorRequestIndex = i + 1;
                    Platform.runLater(() -> {
                        logArea.appendText("错误: ");
                        responseArea.setText(getStackTraceString(e));
                        logger.warn("执行请求出现错误", e);
                        logArea.appendText(getStackTraceString(e) + "\n");
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("执行警告");
                        alert.setHeaderText("请求 " + errorRequestIndex + " 执行遇到问题");
                        alert.setContentText("错误: " + error + "\n\n是否继续执行下一个请求？");

                        ButtonType continueButton = new ButtonType("继续", ButtonBar.ButtonData.YES);
                        ButtonType stopButton = new ButtonType("停止", ButtonBar.ButtonData.NO);
                        alert.getButtonTypes().setAll(continueButton, stopButton);

                        Optional<ButtonType> result = alert.showAndWait();
                        if (!result.isPresent() || result.get() == stopButton) {
                            shouldContinue[0] = false;
                            logger.info("脚本执行已停止\n");
                        }
                    });
                }
            }

            Platform.runLater(() -> {
                statusLabel.setText(shouldContinue[0] ? "执行完成" : "执行已停止");
                closeButton.setDisable(false);
                logger.info("\n" + (shouldContinue[0] ? "脚本执行完成" : "脚本执行已停止") + "\n");
            });
        }).start();
    }

    private void editRequestInDialog(Stage parentDialog, HttpScript script, TableView<HttpRequest> requestTableView, HttpRequest request) {
        Dialog<HttpRequest> dialog = new Dialog<>();
        dialog.setTitle("编辑请求");
        dialog.setHeaderText("编辑请求");
        dialog.initModality(Modality.WINDOW_MODAL);
        dialog.initOwner(parentDialog);

        ButtonType saveButtonType = new ButtonType("保存", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));

        ComboBox<String> methodCombo = new ComboBox<>();
        methodCombo.setItems(FXCollections.observableArrayList("GET", "POST", "PUT", "DELETE", "PATCH"));
        methodCombo.setValue(request.getMethod());

        TextField urlField = new TextField(request.getUrl());
        urlField.setPromptText("URL");

        ComboBox<PackageInfo> packageNameCombo = new ComboBox<>();
        packageNameCombo.setItems(packageList);
        packageNameCombo.setPromptText("选择应用");

        TextField versionCodeField = new TextField();
        versionCodeField.setPromptText("版本号");

        TextField versionNameField = new TextField();
        versionNameField.setPromptText("版本名称");

        VBox mainContainer = new VBox(5);
        mainContainer.setPadding(new Insets(5));

        grid.add(new Label("方法:"), 0, 0);
        grid.add(methodCombo, 1, 0);
        grid.add(new Label("URL:"), 0, 1);
        grid.add(urlField, 1, 1);
        grid.add(new Label("应用包名:"), 0, 2);
        grid.add(packageNameCombo, 1, 2);
        grid.add(new Label("版本号:"), 0, 3);
        grid.add(versionCodeField, 1, 3);
        grid.add(new Label("版本名称:"), 0, 4);
        grid.add(versionNameField, 1, 4);

        // 参数格式化区域（复用添加框的设计）
        HBox parameterContainer = new HBox(10);
        parameterContainer.setPadding(new Insets(5, 0, 5, 0));
        
        VBox parameterBox = new VBox(0);
        parameterBox.setStyle("-fx-border-color: #e0e0e0; -fx-border-width: 1; -fx-border-radius: 4; -fx-padding: 0;");
        parameterBox.setPrefWidth(250);
        
        Label parameterLabel = new Label("参数格式化");
        parameterLabel.setStyle("-fx-font-weight: bold; -fx-padding: 5 5 5 5;");
        
        ComboBox<String> editParamCombo = new ComboBox<>();
        editParamCombo.setPromptText("选择参数");
        editParamCombo.setPrefWidth(240);
        List<String> editComboBoxItems = new ArrayList<>();
        
        Label selectedParamLabel = new Label("已选参数:");
        selectedParamLabel.setStyle("-fx-font-weight: bold; -fx-padding: 5 5 0 5;");
        
        ListView<String> editSelectedParams = new ListView<>();
        editSelectedParams.setPrefHeight(100);
        editSelectedParams.getStyleClass().add("parameter-list");
        ObservableList<String> editSelectedParameters = FXCollections.observableArrayList();
        editSelectedParams.setItems(editSelectedParameters);
        List<String> editSelectedList = new ArrayList<>();

        
        HBox parameterButtons = new HBox(2);
        parameterButtons.setStyle("-fx-padding: 5 5 5 5;");
        Button clearButton = new Button("清空列表");
        Button generateButton = new Button("生成模板");
        parameterButtons.getChildren().addAll(clearButton, generateButton);
        
        parameterBox.getChildren().addAll(parameterLabel, editParamCombo, selectedParamLabel, editSelectedParams, parameterButtons);
        
        // 初始化参数选择器
        ParameterManager paramManager = new ParameterManager();
        List<Parameter> parameters = paramManager.getAvailableParameters();
        editParamCombo.getItems().add("选择参数");
        for (Parameter param : parameters) {
            String displayText = param.getDisplayName() + " (" + param.getName() + ")";
            editParamCombo.getItems().add(displayText);
            editComboBoxItems.add(param.getName());
        }
        editParamCombo.getSelectionModel().select(0);
        
        // 加载已保存的参数列表
        if (request.getParameters() != null && !request.getParameters().isEmpty()) {
            for (String param : request.getParameters()) {
                editSelectedParameters.add(param + " (%" + (editSelectedParameters.size() + 1) + "$s)");
                editSelectedList.add(param);
            }
        }
        
        // 请求体区域
        Label bodyLabel = new Label("请求体:");
        TextArea bodyArea = new TextArea(request.getBody());
        bodyArea.setPromptText("请求体");
        bodyArea.setPrefRowCount(15);
        bodyArea.setMinHeight(300);
        bodyArea.setPrefHeight(300);
        bodyArea.setMaxHeight(300);
        bodyArea.getStyleClass().add("request-body-area");
        bodyArea.setWrapText(true);
        VBox.setVgrow(bodyArea, Priority.ALWAYS);
        
        // 参数选择事件
        editParamCombo.setOnAction(event -> {
            int selectedIndex = editParamCombo.getSelectionModel().getSelectedIndex();
            if (selectedIndex == 0) {
                logger.info("选择了默认项，啥也不干");
                return;
            }
            String paramName = comboBoxItems.get(selectedIndex - 1);
            int parameterIndex = editSelectedParameters.size() + 1;
            String parameterDisplay = paramName + " (%" + parameterIndex + "$s)";
            editSelectedParameters.add(parameterDisplay);
            editSelectedList.add(paramName);
            logger.info("添加了新的参数, 名称为" + paramName + ", 索引为" + parameterIndex);
            editParamCombo.getSelectionModel().select(0);
        });
        
        // 清空列表按钮
        clearButton.setOnAction(event -> {
            editSelectedParameters.clear();
        });
        
        // 生成模板按钮
        generateButton.setOnAction(event -> {
            if (editSelectedParameters.isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "警告", "请先添加参数到列表");
                return;
            }
            
            StringBuilder templateBuilder = new StringBuilder();
            templateBuilder.append("{\n");
            templateBuilder.append("  \"request\": {\n");
            
            for (int i = 0; i < editSelectedList.size(); i++) {
                String param = editSelectedList.get(i);
                
                templateBuilder.append("    \"").append(param).append("\": \"%")
                              .append(i + 1).append("$s\"");
                
                if (i < editSelectedList.size() - 1) {
                    templateBuilder.append(",\n");
                } else {
                    templateBuilder.append("\n");
                }
            }
            
            templateBuilder.append("  }\n");
            templateBuilder.append("}");
            
            bodyArea.setText(templateBuilder.toString());
        });
        
        // 布局容器
        parameterContainer.getChildren().addAll(bodyArea, parameterBox);
        mainContainer.getChildren().addAll(grid, bodyLabel, parameterContainer);

        if (request.getPackageName() != null && !request.getPackageName().isEmpty()) {
            for (PackageInfo info : packageList) {
                if (info.getPackageName() != null && info.getPackageName().equals(request.getPackageName())) {
                    packageNameCombo.setValue(info);
                    break;
                }
            }
        }

        if (request.getVersionCode() != null) {
            versionCodeField.setText(request.getVersionCode().toString());
        } else {
            PackageInfo selected = packageNameCombo.getSelectionModel().getSelectedItem();
            if (selected != null && selected.getVersionCode() != null) {
                versionCodeField.setText(selected.getVersionCode().toString());
            }
        }

        if (request.getVersionName() != null && !request.getVersionName().isEmpty()) {
            versionNameField.setText(request.getVersionName());
        } else {
            PackageInfo selected = packageNameCombo.getSelectionModel().getSelectedItem();
            if (selected != null && selected.getVersionName() != null) {
                versionNameField.setText(selected.getVersionName());
            }
        }

        packageNameCombo.setOnAction(event -> {
            PackageInfo selected = packageNameCombo.getSelectionModel().getSelectedItem();
            if (selected != null) {
                if (selected.getVersionCode() != null) {
                    versionCodeField.setText(selected.getVersionCode().toString());
                } else {
                    versionCodeField.clear();
                }
                if (selected.getVersionName() != null) {
                    versionNameField.setText(selected.getVersionName());
                } else {
                    versionNameField.clear();
                }
            }
        });

        javafx.scene.Node saveButton = dialog.getDialogPane().lookupButton(saveButtonType);
        saveButton.setDisable(urlField.getText().trim().isEmpty());
        saveButton.setStyle("-fx-min-height: 32; -fx-pref-height: 32;");

        urlField.textProperty().addListener((observable, oldValue, newValue) -> {
            saveButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(mainContainer);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                request.setMethod(methodCombo.getValue());
                request.setUrl(urlField.getText().trim());
                PackageInfo selectedPackage = packageNameCombo.getSelectionModel().getSelectedItem();
                request.setPackageName(selectedPackage != null ? selectedPackage.getPackageName() : "");
                
                if (!versionCodeField.getText().trim().isEmpty()) {
                    try {
                        request.setVersionCode(Integer.parseInt(versionCodeField.getText().trim()));
                    } catch (NumberFormatException e) {
                        request.setVersionCode(null);
                    }
                } else {
                    request.setVersionCode(null);
                }
                
                request.setVersionName(versionNameField.getText().trim());
                request.setBody(bodyArea.getText());
                request.setParameters(editSelectedList);
                logger.info("请求编辑完成");
                return request;
            }
            return null;
        });

        Optional<HttpRequest> result = dialog.showAndWait();
        result.ifPresent(updatedRequest -> {
            int index = script.getRequests().indexOf(request);
            logger.info("请求索引: " + index + ", 请求内容: " + updatedRequest);
            if (index >= 0) {
                script.getRequests().set(index, updatedRequest);
                requestTableView.setItems(FXCollections.observableArrayList(script.getRequests()));
                saveScripts();
                updateRequestTableView(script);
                logger.info("请求已更新: " + updatedRequest.getMethod() + " " + updatedRequest.getUrl());
            }
        });
    }

    /**
     * 检查是否有未保存的配置更改
     */
    private boolean hasUnsavedChanges() {
        DeviceInfo current = deviceManager.getCurrentDevice();
        if (current == null) {
            return false;
        }
        
        ContextManager context = current.getContext();
        if (context == null) {
            return false;
        }
        
        // 检查设备名是否更改
        if (deviceNameField != null && !deviceNameField.getText().equals(current.getDeviceName())) {
            return true;
        }
        
        // 检查其他关键字段是否更改
        if (bindNumberField != null && !bindNumberField.getText().equals(context.getBindNumber())) {
            return true;
        }
        
        if (watchIdField != null && !watchIdField.getText().equals(context.getWatchId())) {
            return true;
        }
        
        if (modelField != null && !modelField.getText().equals(context.getWatchModel())) {
            return true;
        }
        
        // 检查应用信息是否更改
        if (packageNameCombo != null) {
            PackageInfo selectedPackage = packageNameCombo.getSelectionModel().getSelectedItem();
            if (selectedPackage != null && !selectedPackage.getPackageName().equals(context.getPackageName())) {
                return true;
            }
        }
        
        return false;
    }
    
    @FXML
    private void onClose() {
        // 检查是否有未保存的配置更改
        if (hasUnsavedChanges()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("确认退出");
            alert.setHeaderText("有未保存的配置更改");
            alert.setContentText("是否要保存当前配置后再退出？");
            
            ButtonType saveButton = new ButtonType("保存并退出");
            ButtonType exitWithoutSaveButton = new ButtonType("不保存退出");
            ButtonType cancelButton = new ButtonType("取消", ButtonBar.ButtonData.CANCEL_CLOSE);
            
            alert.getButtonTypes().setAll(saveButton, exitWithoutSaveButton, cancelButton);
            
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent()) {
                if (result.get() == saveButton) {
                    // 保存配置并退出
                    onUpdateParams();
                    System.exit(0);
                } else if (result.get() == exitWithoutSaveButton) {
                    // 不保存直接退出
                    System.exit(0);
                }
                // 如果选择取消，什么都不做
            }
        } else {
            // 没有未保存的更改，直接退出
            System.exit(0);
        }
    }


    private void showAlert(Alert.AlertType type, String title, String content) {
        Platform.runLater(() -> {
            Alert alert = new Alert(type);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(content);
            alert.showAndWait();
        });
    }
    
    private static class DeviceListCell extends TextFieldListCell<DeviceInfo> {
        @Override
        public void updateItem(DeviceInfo device, boolean empty) {
            super.updateItem(device, empty);
            if (empty || device == null) {
                setText(null);
                setGraphic(null);
            } else {
                DeviceInfo current = new DeviceManager().getCurrentDevice();
                boolean isCurrent = current != null && current.getBindNumber().equals(device.getBindNumber());
                String prefix = isCurrent ? "★ " : "  ";
                setText(prefix + device.getDeviceName() + " (" + device.getBindNumber() + ")");
            }
        }
    }
}
