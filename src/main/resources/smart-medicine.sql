SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for feedback
-- ----------------------------
DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback`
(
    `id`          int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `name`        varchar(11)  DEFAULT NULL COMMENT '反馈用户',
    `email`       varchar(255) DEFAULT NULL COMMENT '邮箱地址',

    `title`       varchar(255) DEFAULT NULL COMMENT '反馈标题',
    `content`     text COMMENT '反馈内容',
    `create_time` datetime     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 8
  DEFAULT CHARSET = utf8mb4;

-- ----------------------------
-- Records of feedback
-- ----------------------------
BEGIN;
INSERT INTO `feedback` (`id`, `name`, `email`, `title`, `content`, `create_time`, `update_time`)
VALUES (6, '路人甲', '31952874@qq.com', '测试一号', '测试这个系统有问题吗？', '2024-03-26 14:23:59',
        '2024-03-26 14:23:59');
INSERT INTO `feedback` (`id`, `name`, `email`, `title`, `content`, `create_time`, `update_time`)
VALUES (7, '路人乙', '31952874@qq.com', '测试二号', '惆怅长岑长错错错错错错', '2024-03-26 14:24:20',
        '2024-03-26 14:24:20');
COMMIT;

-- ----------------------------
-- Table structure for history
-- ----------------------------
DROP TABLE IF EXISTS `history`;
CREATE TABLE `history`
(
    `id`           int(11) NOT NULL AUTO_INCREMENT COMMENT '用户搜索历史主键id',
    `user_id`      int(11)      DEFAULT NULL COMMENT '用户ID',
    `keyword`      varchar(255) DEFAULT NULL COMMENT '搜索关键字',
    `operate_type` int(1)       DEFAULT NULL COMMENT '类型：1搜索，2科目，3药品',
    `create_time`  datetime     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`  datetime     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 160
  DEFAULT CHARSET = utf8mb4;

-- ----------------------------
-- Records of history
-- ----------------------------
BEGIN;
INSERT INTO `history` (`id`, `user_id`, `keyword`, `operate_type`, `create_time`, `update_time`)
VALUES (126, 4, '10,无', 1, '2024-03-26 16:09:34', '2024-03-26 16:09:34');
INSERT INTO `history` (`id`, `user_id`, `keyword`, `operate_type`, `create_time`, `update_time`)
VALUES (127, 4, '10,无', 1, '2024-03-26 16:09:40', '2024-03-26 16:09:40');
INSERT INTO `history` (`id`, `user_id`, `keyword`, `operate_type`, `create_time`, `update_time`)
VALUES (128, 4, '病毒性感冒', 2, '2024-03-26 16:09:48', '2024-03-26 16:09:48');
INSERT INTO `history` (`id`, `user_id`, `keyword`, `operate_type`, `create_time`, `update_time`)
VALUES (129, 4, '10,无', 1, '2024-03-26 16:09:52', '2024-03-26 16:09:52');
INSERT INTO `history` (`id`, `user_id`, `keyword`, `operate_type`, `create_time`, `update_time`)
VALUES (130, 4, '湿疹', 2, '2024-03-26 14:22:15', '2024-03-26 14:22:15');
INSERT INTO `history` (`id`, `user_id`, `keyword`, `operate_type`, `create_time`, `update_time`)
VALUES (131, 4, '偏头痛', 2, '2024-03-26 14:22:49', '2024-03-26 14:22:49');
INSERT INTO `history` (`id`, `user_id`, `keyword`, `operate_type`, `create_time`, `update_time`)
VALUES (132, 5, '7,无', 1, '2024-03-26 14:27:41', '2024-03-26 14:27:41');
INSERT INTO `history` (`id`, `user_id`, `keyword`, `operate_type`, `create_time`, `update_time`)
VALUES (133, 5, '湿疹', 2, '2024-03-26 14:27:53', '2024-03-26 14:27:53');
INSERT INTO `history` (`id`, `user_id`, `keyword`, `operate_type`, `create_time`, `update_time`)
VALUES (134, 5, '感冒', 2, '2024-03-26 14:28:08', '2024-03-26 14:28:08');
INSERT INTO `history` (`id`, `user_id`, `keyword`, `operate_type`, `create_time`, `update_time`)
VALUES (135, 5, '17,无', 1, '2024-03-26 14:28:22', '2024-03-26 14:28:22');
INSERT INTO `history` (`id`, `user_id`, `keyword`, `operate_type`, `create_time`, `update_time`)
VALUES (136, 5, '17,溃疡', 1, '2024-03-26 14:28:28', '2024-03-26 14:28:28');
INSERT INTO `history` (`id`, `user_id`, `keyword`, `operate_type`, `create_time`, `update_time`)
VALUES (137, 5, '溃疡', 2, '2024-03-26 14:28:28', '2024-03-26 14:28:28');
INSERT INTO `history` (`id`, `user_id`, `keyword`, `operate_type`, `create_time`, `update_time`)
VALUES (138, 5, '17,溃疡', 1, '2024-03-26 16:26:48', '2024-03-26 16:26:48');
INSERT INTO `history` (`id`, `user_id`, `keyword`, `operate_type`, `create_time`, `update_time`)
VALUES (139, 5, '溃疡', 2, '2024-03-26 16:26:48', '2024-03-26 16:26:48');
INSERT INTO `history` (`id`, `user_id`, `keyword`, `operate_type`, `create_time`, `update_time`)
VALUES (140, 5, '17,溃疡', 1, '2024-03-26 16:28:20', '2024-03-26 16:28:20');
INSERT INTO `history` (`id`, `user_id`, `keyword`, `operate_type`, `create_time`, `update_time`)
VALUES (141, 5, '溃疡', 2, '2024-03-26 16:28:20', '2024-03-26 16:28:20');
INSERT INTO `history` (`id`, `user_id`, `keyword`, `operate_type`, `create_time`, `update_time`)
VALUES (142, 5, '17,溃疡', 1, '2024-03-26 16:33:52', '2024-03-26 16:33:52');
INSERT INTO `history` (`id`, `user_id`, `keyword`, `operate_type`, `create_time`, `update_time`)
VALUES (143, 5, '溃疡', 2, '2024-03-26 16:33:52', '2024-03-26 16:33:52');
INSERT INTO `history` (`id`, `user_id`, `keyword`, `operate_type`, `create_time`, `update_time`)
VALUES (144, 5, '溃疡', 2, '2024-03-26 16:34:08', '2024-03-26 16:34:08');
INSERT INTO `history` (`id`, `user_id`, `keyword`, `operate_type`, `create_time`, `update_time`)
VALUES (145, 5, '7,无', 1, '2024-03-26 16:37:57', '2024-03-26 16:37:57');
INSERT INTO `history` (`id`, `user_id`, `keyword`, `operate_type`, `create_time`, `update_time`)
VALUES (146, 5, '9,无', 1, '2024-03-26 16:38:34', '2024-03-26 16:38:34');
INSERT INTO `history` (`id`, `user_id`, `keyword`, `operate_type`, `create_time`, `update_time`)
VALUES (147, 5, '9,无', 1, '2024-03-26 16:41:59', '2024-03-26 16:41:59');
INSERT INTO `history` (`id`, `user_id`, `keyword`, `operate_type`, `create_time`, `update_time`)
VALUES (148, 5, '9,无', 1, '2024-03-26 16:42:14', '2024-03-26 16:42:14');
INSERT INTO `history` (`id`, `user_id`, `keyword`, `operate_type`, `create_time`, `update_time`)
VALUES (149, 5, '9,无', 1, '2024-03-26 16:42:45', '2024-03-26 16:42:45');
INSERT INTO `history` (`id`, `user_id`, `keyword`, `operate_type`, `create_time`, `update_time`)
VALUES (150, 5, '9,无', 1, '2024-03-26 16:43:54', '2024-03-26 16:43:54');
INSERT INTO `history` (`id`, `user_id`, `keyword`, `operate_type`, `create_time`, `update_time`)
VALUES (151, 5, '9,无', 1, '2024-03-26 16:44:26', '2024-03-26 16:44:26');
INSERT INTO `history` (`id`, `user_id`, `keyword`, `operate_type`, `create_time`, `update_time`)
VALUES (152, 5, '9,无', 1, '2024-03-26 16:44:45', '2024-03-26 16:44:45');
INSERT INTO `history` (`id`, `user_id`, `keyword`, `operate_type`, `create_time`, `update_time`)
VALUES (153, 5, '2,无', 1, '2024-03-26 16:44:51', '2024-03-26 16:44:51');
INSERT INTO `history` (`id`, `user_id`, `keyword`, `operate_type`, `create_time`, `update_time`)
VALUES (154, 5, '2,无', 1, '2024-03-26 16:45:46', '2024-03-26 16:45:46');
INSERT INTO `history` (`id`, `user_id`, `keyword`, `operate_type`, `create_time`, `update_time`)
VALUES (155, 5, '1', 3, '2024-03-26 15:34:34', '2024-03-26 15:34:34');
INSERT INTO `history` (`id`, `user_id`, `keyword`, `operate_type`, `create_time`, `update_time`)
VALUES (156, 4, '牙周炎', 2, '2024-03-27 19:32:05', '2024-03-27 19:32:05');
INSERT INTO `history` (`id`, `user_id`, `keyword`, `operate_type`, `create_time`, `update_time`)
VALUES (157, 4, '9,无', 1, '2024-03-27 19:32:52', '2024-03-27 19:32:52');
INSERT INTO `history` (`id`, `user_id`, `keyword`, `operate_type`, `create_time`, `update_time`)
VALUES (158, 4, '1,无', 1, '2024-03-27 19:32:56', '2024-03-27 19:32:56');
INSERT INTO `history` (`id`, `user_id`, `keyword`, `operate_type`, `create_time`, `update_time`)
VALUES (159, 4, '17,无', 1, '2024-03-27 19:32:59', '2024-03-27 19:32:59');
COMMIT;

-- ----------------------------
-- Table structure for illness
-- ----------------------------
DROP TABLE IF EXISTS `illness`;
CREATE TABLE `illness`
(
    `id`              int(1) NOT NULL AUTO_INCREMENT COMMENT '疾病id',
    `kind_id`         int(11)      DEFAULT NULL COMMENT '疾病分类ID',
    `illness_name`    varchar(100) DEFAULT NULL COMMENT '疾病名字',
    `include_reason`  mediumtext COMMENT '诱发因素',
    `illness_symptom` mediumtext COMMENT '疾病症状',
    `special_symptom` mediumtext COMMENT '特殊症状',
    `create_time`     datetime     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`     datetime     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 14
  DEFAULT CHARSET = utf8mb4;

-- ----------------------------
-- Records of illness
-- ----------------------------
BEGIN;
INSERT INTO `illness` (`id`, `kind_id`, `illness_name`, `include_reason`, `illness_symptom`, `special_symptom`,
                       `create_time`, `update_time`)
VALUES (1, 10, '病毒性感冒', '各种导致全身或呼吸道局部防御功能降低的原因，如受凉、淋雨、气候突变、过度疲劳等均可诱发本疲',
        '急性起病，患者主要表现为鼻塞、流涕、咽痛、咳嗽等症状。部分患者可有发热、乏力、头痛、周身酸痛、\n食欲减退、腹胀、便秘或腹泻等全身症状。',
        '急性起病，患者主要表现为鼻塞、流涕、咽痛、咳嗽等症状。部分患者可有发热、乏力、头痛、周身酸痛、\n食欲减退、腹胀、便秘或腹泻等全身症状。',
        '2024-03-01 11:31:10', '2024-03-26 16:00:41');
INSERT INTO `illness` (`id`, `kind_id`, `illness_name`, `include_reason`, `illness_symptom`, `special_symptom`,
                       `create_time`, `update_time`)
VALUES (2, 10, '风寒感冒', '身体免疫力低下的情况下，体内有寒气入侵，导致感冒',
        '恶寒重、发热轻、无汗、头痛身痛、鼻塞流清涕、咳嗽吐稀白痰、口不渴或渴喜热饮、苔薄白',
        '恶寒重、发热轻、无汗、头痛身痛、畏寒', '2024-03-01 11:31:10', '2024-03-26 16:01:51');
INSERT INTO `illness` (`id`, `kind_id`, `illness_name`, `include_reason`, `illness_symptom`, `special_symptom`,
                       `create_time`, `update_time`)
VALUES (3, 2, '扁桃体发炎',
        '扁桃体炎形成的原因与多种因素有关，包括感染因素、免疫因素、邻近器官的急性炎症等，细菌和病毒积存\n于扁桃体窝引起该病。扁桃体炎还可继发于某些急性传染病，如猩红热、麻疹、流感等。',
        '发热、咽部不适、咽部疼痛，甚至吞咽、呼吸困难、局部可有咽痛，吞咽时尤为明显，甚至因畏惧疼痛不敢吞咽，疼痛可放射至耳部，幼儿常因不能吞咽而拒食哭闹。',
        '咽痛、咽部不适', '2024-03-01 11:31:10', '2024-03-01 17:49:05');
INSERT INTO `illness` (`id`, `kind_id`, `illness_name`, `include_reason`, `illness_symptom`, `special_symptom`,
                       `create_time`, `update_time`)
VALUES (4, 3, '偏头痛', '偏头痛的病因尚不明确，可能与遗传、内分泌代谢、环境因素、精神因素有关。',
        '偏头痛的常见症状包括：头痛，开始常为隐约疼痛，逐渐变为搏动性疼痛，活动时加重，还可从头的一侧转\n移至另一侧，累及头前部或整个头部；对光线、噪音和气味敏感；伴有恶心、呕吐，胃部不适，腹部疼痛；\n食欲差；感觉非常的暖或冷；肤色苍白；疲劳；头晕；视野模糊；腹泻。比较军见的症状包括发烧、影响正\n常的肢体活动。',
        '左侧疼痛、右侧疼痛、单侧疼痛、一阵一阵的疼痛、像针扎一样', '2024-03-01 11:31:10', '2024-03-01 17:49:06');
INSERT INTO `illness` (`id`, `kind_id`, `illness_name`, `include_reason`, `illness_symptom`, `special_symptom`,
                       `create_time`, `update_time`)
VALUES (5, 2, '便秘',
        '便秘通常是由于美便在消化道中移动太慢，或无法从直肠中有效清除时，导致类便脱水、变硬和干燥，从而引发的便秘。',
        '排便次数减少、一周内小于3次、粪便干燥或结块、如羊粪、排便因难，如排便时间长、排便时感觉有阻碍、排便后仍有粪便未排尽的感觉、需手按腹部帮助排便等',
        '大便困难、拉不出来', '2024-03-01 11:31:10', '2024-03-26 16:03:44');
INSERT INTO `illness` (`id`, `kind_id`, `illness_name`, `include_reason`, `illness_symptom`, `special_symptom`,
                       `create_time`, `update_time`)
VALUES (6, 3, '骨折',
        '骨折是由创伤或骨骼痪病所导致，大部分骨折都是由于直接或间接暴力引起。跌倒、撞击、交通意外等暴力因素是导致骨折的常见原因。积累性劳损及骨骼痪病也会增加骨折发生几率，骨骼痪病（如骨髓炎、骨肿瘤）导致骨质破坏，患者受到轻微外力就可能发生骨折。',
        '骨折特有特征为畸形、异常活动和骨擦音（感）。大部分骨折一般只引起局部症状，最常见的症状就是局部\n疼痛、肿胀及功能障碍。严重骨折和多发性骨折可伴随全身症状（如休克、发热）。',
        '骨折的一般表现为局部疼痛、肿胀及功能障碍', '2024-03-01 11:31:10', '2024-03-26 16:04:23');
INSERT INTO `illness` (`id`, `kind_id`, `illness_name`, `include_reason`, `illness_symptom`, `special_symptom`,
                       `create_time`, `update_time`)
VALUES (7, 17, '牙周炎',
        '牙周炎是一种破坏性庆病，与微生物、宿主反应有关，是导致我国成人牙齿丧失的主要原因，严重影响患者的口腔健康。在局部致病因素中，牙菌斑是最主要的致病因素，而在全身因素中吸烟是高危因素。',
        '健康的牙龈应该呈粉红色，边缘薄且紧贴牙面，质坚韧，探诊不出血。牙周災的主要症状是牙龈红肿、质地\n松软、探诊出血、牙周袋溢脓和牙齿松动。',
        '牙龈出血、牙齿松动、牙龈肿', '2024-03-01 11:31:10', '2024-03-26 16:04:43');
INSERT INTO `illness` (`id`, `kind_id`, `illness_name`, `include_reason`, `illness_symptom`, `special_symptom`,
                       `create_time`, `update_time`)
VALUES (8, 2, '胃溃疡',
        '胃溃疡是一种常见的消化痪病，任何年龄的人都可能患病。在全球范围内，约占10%的人群一生中都会患有消化性渍疡。在患病人群中，40-60岁的中老年患者最为多见，而且男性多于女性。',
        '胃溃疡的症状较多，包括胃部疼痛、食欲不振、餐后腹胀或胃部不适、体重减轻等等。这些症状的严重程度\n取决于溃疡的严重程度。有些患者可能没有任何症状（如“无症状性溃疡\"），或者是以胃出血、胃穿孔等并\n发症为首发症状。',
        '餐后腹胀、体重减轻、食欲不振', '2024-03-01 11:31:10', '2024-03-26 16:05:16');
INSERT INTO `illness` (`id`, `kind_id`, `illness_name`, `include_reason`, `illness_symptom`, `special_symptom`,
                       `create_time`, `update_time`)
VALUES (9, 17, '口腔溃疡',
        '口腔渍疡的致病原因尚不明确，多种因素可诱发，包括遗传因素、饮食因素、免疫因素等，且具有明显的个体差异。口腔渍疡经常、反复发作时，严重影响患者的日常生活和工作。',
        '口腔溃疡常见于口腔的唇、脸颊、软腭或牙龈等处的黏膜上，溃疡面一般呈圆形或椭圆形，溃疡面凹陷、有\n白色或黄色的中心、周围充血微红肿，有明显疼痛感。',
        '口腔溃疡常见于口腔的唇、脸颊、软腭或牙龈等处的黏膜上，溃疡面一般呈圆形或椭圆形，溃疡面凹陷、有\n白色或黄色的中心、周围充血微红肿，有明显疼痛感。',
        '2024-03-01 11:31:10', '2024-03-26 16:05:51');
INSERT INTO `illness` (`id`, `kind_id`, `illness_name`, `include_reason`, `illness_symptom`, `special_symptom`,
                       `create_time`, `update_time`)
VALUES (13, 7, '湿疹',
        '湿疹的病因目前尚不明确，与机体内因、外因、社会心理因素等都有关。机体内因包括免疫功能异常和系统性痪病（如内分泌痪病、营养障碍、慢性感染等）以及遗传性或获得性 皮肤屏障功能障碍。',
        '急性期表现为红斑、水肿、粟粒大小的丘疹、丘疱疹、水疱，糜烂及渗出；亚急性期表现为红肿和渗出减\n轻，糜烂面结痂、脱屑；慢性期主要表现为粗糙肥厚、苔藓样变。湿疹容易复发，严重影响患者的生活质\n量。',
        '起病较急、发病较快，瘙痒剧烈。', '2024-03-26 16:08:58', '2024-03-26 16:09:11');
COMMIT;

-- ----------------------------
-- Table structure for illness_kind
-- ----------------------------
DROP TABLE IF EXISTS `illness_kind`;
CREATE TABLE `illness_kind`
(
    `id`          int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `name`        varchar(255) DEFAULT NULL COMMENT '分类名称',
    `info`        varchar(255) DEFAULT NULL COMMENT '描述',
    `create_time` datetime     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 18
  DEFAULT CHARSET = utf8mb4;

-- ----------------------------
-- Records of illness_kind
-- ----------------------------
BEGIN;
INSERT INTO `illness_kind` (`id`, `name`, `info`, `create_time`, `update_time`)
VALUES (1, '急诊科', '急诊科疾病', '2024-03-01 11:57:39', '2024-03-01 12:01:00');
INSERT INTO `illness_kind` (`id`, `name`, `info`, `create_time`, `update_time`)
VALUES (2, '内科', '内科疾病', '2024-03-01 11:57:57', '2024-03-01 12:00:59');
INSERT INTO `illness_kind` (`id`, `name`, `info`, `create_time`, `update_time`)
VALUES (3, '外科', '外科疾病', '2024-03-01 11:58:26', '2024-03-01 12:00:57');
INSERT INTO `illness_kind` (`id`, `name`, `info`, `create_time`, `update_time`)
VALUES (4, '妇产科', '妇产科疾病', '2024-03-01 11:58:36', '2024-03-01 12:00:56');
INSERT INTO `illness_kind` (`id`, `name`, `info`, `create_time`, `update_time`)
VALUES (5, '儿科', '儿科疾病', '2024-03-01 11:58:49', '2024-03-01 12:00:54');
INSERT INTO `illness_kind` (`id`, `name`, `info`, `create_time`, `update_time`)
VALUES (6, '男科', '男科疾病', '2024-03-01 11:58:59', '2024-03-01 12:00:53');
INSERT INTO `illness_kind` (`id`, `name`, `info`, `create_time`, `update_time`)
VALUES (7, '皮肤科', '皮肤科疾病', '2024-03-26 16:07:12', '2024-03-26 16:07:12');
INSERT INTO `illness_kind` (`id`, `name`, `info`, `create_time`, `update_time`)
VALUES (9, '肝病', '肝病疾病', '2024-03-01 11:59:27', '2024-03-01 12:00:49');
INSERT INTO `illness_kind` (`id`, `name`, `info`, `create_time`, `update_time`)
VALUES (10, '传染科', '传染科疾病', '2024-03-01 11:59:35', '2024-03-01 12:00:48');
INSERT INTO `illness_kind` (`id`, `name`, `info`, `create_time`, `update_time`)
VALUES (16, '耳鼻喉科', '耳鼻喉科疾病', '2024-03-01 12:00:23', '2024-03-01 12:00:41');
INSERT INTO `illness_kind` (`id`, `name`, `info`, `create_time`, `update_time`)
VALUES (17, '口腔科', '口腔科疾病', '2024-03-01 12:00:31', '2024-03-01 12:00:39');
COMMIT;

-- ----------------------------
-- Table structure for illness_medicine
-- ----------------------------
DROP TABLE IF EXISTS `illness_medicine`;
CREATE TABLE `illness_medicine`
(
    `id`          int(1) NOT NULL AUTO_INCREMENT COMMENT '病和药品关联id',
    `illness_id`  int(1)   DEFAULT NULL COMMENT '病id',
    `medicine_id` int(1)   DEFAULT NULL COMMENT '药品id',
    `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 21
  DEFAULT CHARSET = utf8mb4;

-- ----------------------------
-- Records of illness_medicine
-- ----------------------------
BEGIN;
INSERT INTO `illness_medicine` (`id`, `illness_id`, `medicine_id`, `create_time`, `update_time`)
VALUES (6, 3, 1, '2024-03-26 14:20:35', '2024-03-26 14:20:35');
INSERT INTO `illness_medicine` (`id`, `illness_id`, `medicine_id`, `create_time`, `update_time`)
VALUES (7, 2, 1, '2024-03-26 14:20:37', '2024-03-26 14:20:37');
INSERT INTO `illness_medicine` (`id`, `illness_id`, `medicine_id`, `create_time`, `update_time`)
VALUES (8, 1, 1, '2024-03-26 14:20:38', '2024-03-26 14:20:38');
INSERT INTO `illness_medicine` (`id`, `illness_id`, `medicine_id`, `create_time`, `update_time`)
VALUES (9, 4, 1, '2024-03-26 14:20:42', '2024-03-26 14:20:42');
INSERT INTO `illness_medicine` (`id`, `illness_id`, `medicine_id`, `create_time`, `update_time`)
VALUES (10, 7, 1, '2024-03-26 14:20:44', '2024-03-26 14:20:44');
INSERT INTO `illness_medicine` (`id`, `illness_id`, `medicine_id`, `create_time`, `update_time`)
VALUES (11, 1, 2, '2024-03-26 14:20:59', '2024-03-26 14:20:59');
INSERT INTO `illness_medicine` (`id`, `illness_id`, `medicine_id`, `create_time`, `update_time`)
VALUES (12, 2, 2, '2024-03-26 14:21:01', '2024-03-26 14:21:01');
INSERT INTO `illness_medicine` (`id`, `illness_id`, `medicine_id`, `create_time`, `update_time`)
VALUES (13, 5, 3, '2024-03-26 14:21:16', '2024-03-26 14:21:16');
INSERT INTO `illness_medicine` (`id`, `illness_id`, `medicine_id`, `create_time`, `update_time`)
VALUES (14, 13, 5, '2024-03-26 14:21:29', '2024-03-26 14:21:29');
INSERT INTO `illness_medicine` (`id`, `illness_id`, `medicine_id`, `create_time`, `update_time`)
VALUES (15, 8, 4, '2024-03-26 14:21:39', '2024-03-26 14:21:39');
INSERT INTO `illness_medicine` (`id`, `illness_id`, `medicine_id`, `create_time`, `update_time`)
VALUES (16, 7, 6, '2024-03-26 14:21:50', '2024-03-26 14:21:50');
INSERT INTO `illness_medicine` (`id`, `illness_id`, `medicine_id`, `create_time`, `update_time`)
VALUES (17, 4, 7, '2024-03-26 14:22:01', '2024-03-26 14:22:01');
INSERT INTO `illness_medicine` (`id`, `illness_id`, `medicine_id`, `create_time`, `update_time`)
VALUES (18, 2, 7, '2024-03-26 14:22:03', '2024-03-26 14:22:03');
INSERT INTO `illness_medicine` (`id`, `illness_id`, `medicine_id`, `create_time`, `update_time`)
VALUES (19, 1, 7, '2024-03-26 14:22:04', '2024-03-26 14:22:04');
INSERT INTO `illness_medicine` (`id`, `illness_id`, `medicine_id`, `create_time`, `update_time`)
VALUES (20, 3, 7, '2024-03-26 14:22:05', '2024-03-26 14:22:05');
COMMIT;

-- ----------------------------
-- Table structure for medicine
-- ----------------------------
DROP TABLE IF EXISTS `medicine`;
CREATE TABLE `medicine`
(
    `id`              int(1) NOT NULL AUTO_INCREMENT COMMENT '药品主键ID',
    `medicine_name`   varchar(100)   DEFAULT NULL COMMENT '药的名字',
    `keyword`         varchar(255)   DEFAULT NULL COMMENT '关键字搜索',
    `medicine_effect` mediumtext COMMENT '药的功效',
    `medicine_brand`  varchar(255)   DEFAULT NULL COMMENT '药的品牌',
    `interaction`     mediumtext COMMENT '药的相互作用',
    `taboo`           mediumtext COMMENT '禁忌',
    `us_age`          mediumtext COMMENT '用法用量',
    `medicine_type`   int(1)         DEFAULT NULL COMMENT '药的类型，0西药，1中药，2中成药',
    `img_path`        varchar(255)   DEFAULT NULL COMMENT '相关图片路径',
    `medicine_price`  decimal(10, 2) DEFAULT NULL COMMENT '药的价格',
    `create_time`     datetime       DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`     datetime       DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 8
  DEFAULT CHARSET = utf8mb4;

-- ----------------------------
-- Records of medicine
-- ----------------------------
BEGIN;
INSERT INTO `medicine` (`id`, `medicine_name`, `keyword`, `medicine_effect`, `medicine_brand`, `interaction`, `taboo`,
                        `us_age`, `medicine_type`, `img_path`, `medicine_price`, `create_time`, `update_time`)
VALUES (1, '阿莫西林胶囊', '消炎药、感冒药',
        '本品尚可用于治疗伤寒、伤寒带菌者及钩端螺旋体病；阿莫西林亦可与克拉霉素、兰索拉唑三联用药根除胃、十二指肠幽门螺杆菌，降低消化道溃疡复发率。',
        '仁和',
        '1．丙磺舒竞争性地减少本品的肾小管分泌，两者同时应用可引起阿莫西林血浓度升高、半衰期延长。\n2．氯霉素、大环内酯类、磺胺类和四环素类药物在体外干扰阿莫西林的抗菌作用，但其临床意义不明。',
        '1. 青霉素类口服药可引起过敏性休克，有多见于青霉素或头孢菌素过敏史的患者。用药前必须详细询问药物过敏史并做青霉素皮肤试验。如发生过敏性休克，应就地抢救，予以保持气道畅通、吸氧及应用肾上腺素、糖皮质激素等治疗措施。\n2.传染性单核细胞增多症患者应用本品易发生皮疹，应避免使用。\n3.疗程较长患者应检查肝、肾功能和血常规。\n4.阿莫西林可导致采用Benedict或Fehling试剂的尿糖试验出现假阳性。\n5.下列情况应慎用：\n(1)有哮喘、枯草热等过敏性疾病史者。\n(2)老年人和肾功能严重损害时可能须调整剂量。',
        '成人的具体使用剂量为0.5g/次，每6-8小时重复用药，24小时内服用剂量不能超过4g。儿童一日用药剂量按照患者实际体重为20-40mg/kg，重复用药间隔时长为8h/次。',
        1, 'https://kagg886.oss-cn-beijing.aliyuncs.com/%E9%98%BF%E8%8E%AB%E8%A5%BF%E6%9E%97.jpeg', 14.00,
        '2024-03-02 11:46:00', '2024-03-26 14:20:46');
INSERT INTO `medicine` (`id`, `medicine_name`, `keyword`, `medicine_effect`, `medicine_brand`, `interaction`, `taboo`,
                        `us_age`, `medicine_type`, `img_path`, `medicine_price`, `create_time`, `update_time`)
VALUES (2, '999感冒灵颗粒', '感冒药、流鼻涕、发烧', '解热镇痛功效，用于因感冒引起的头痛，发热，鼻塞，流涕，咽痛等症状。', '999',
        '三九感冒灵颗粒是复方药，里面含有中西药成分，不宜和西药感冒药同服。如果两种药中含有同一种成分，就只能选择服用其中一种，以免使摄入药量加倍，增加毒性，成为重复用药 [3]  。比如，三九感冒灵颗粒和西药泰诺，都含有解热镇痛效果的“扑热息痛”成分，若是两种药一起吃，过量的“扑热息痛”会对人体肝脏造成损害。',
        '1.忌烟，酒及辛辣，生冷，油腻食物。\n2.不宜在服药期间同时服用滋补性中成药\n3. 高血压、心脏病、肝病、肾病等慢性病严重者应在医师指导下服用。\n4.本品含对乙氨基酚，马来酸氨苯那敏，咖啡因。服用本品期间不得饮酒或含有酒精的饮料；不能同时服用与本品成分相似的其它抗感冒药；肝，肾功能不全者慎用；膀胱颈梗阻，甲状腺功能亢进，青光眼，高血压和前列腺肥大者慎用；孕妇及哺乳期妇女慎用；服药期间不得驾驶机，车，船，从事高空作业，机械作业及操作精密仪器。\n5.脾胃虚寒，症见腹痛，喜暖，泄泻者慎用。\n6.糖尿病患者、消化道溃疡患者、膀胱颈梗阻、幽门十二指肠梗阻、甲状腺机能亢进、青光眼以及前列腺肥大等患者慎用。\n7.儿童，年老体弱者应在医师指导下使用。\n8.服药3天后症状无改善，或症状加重，或出现新的严重症状如胸闷，心悸等应立即停药，并去医院就诊。\n9.对本药过敏者禁用，过敏体质者慎用。\n10. 本品性状发生改变时禁止使用。\n11.儿童必须在成人监护下使用。\n12.请将本品放在儿童不能接触的地方。\n13.如正在使用其他药品，使用本品前请咨询医师或药师.',
        '开水冲服，一次1袋，一日3次。小儿减量或遵医嘱。', 2,
        'https://kagg886.oss-cn-beijing.aliyuncs.com/999%E6%84%9F%E5%86%92%E7%81%B5.jpeg', 39.80,
        '2024-03-02 11:50:13', '2024-03-26 12:47:16');
INSERT INTO `medicine` (`id`, `medicine_name`, `keyword`, `medicine_effect`, `medicine_brand`, `interaction`, `taboo`,
                        `us_age`, `medicine_type`, `img_path`, `medicine_price`, `create_time`, `update_time`)
VALUES (3, '开塞露', '便秘',
        '都是利用甘油或山梨醇的高浓度，即高渗作用，软化大便，刺激肠壁，反射性地引起排便反应，再加上其具有润滑作用，能使大便容易排出',
        '信龙', NULL,
        '1.刺破或剪开后的注药导管的开口应光滑，以免擦伤肛门或直肠。\n2.对本品过敏者禁用，过敏体质者慎用。\n3.本品性状发生改变时禁止使用。\n4.请将本品放在儿童不能接触的地方。\n5.儿童必须在成人监护下使用。\n6.如正在使用其他药品，使用本品前请咨询医师或药师。',
        '将容器顶端刺破或剪开，涂以油脂少许，缓慢插入肛门，然后将药液挤入直肠内，成人一次1支，儿童一次\n0.5支。', 0,
        'https://kagg886.oss-cn-beijing.aliyuncs.com/%E5%BC%80%E5%A1%9E%E9%9C%B2.jpeg', 18.00,
        '2024-03-02 12:52:13', '2024-03-26 12:49:31');
INSERT INTO `medicine` (`id`, `medicine_name`, `keyword`, `medicine_effect`, `medicine_brand`, `interaction`, `taboo`,
                        `us_age`, `medicine_type`, `img_path`, `medicine_price`, `create_time`, `update_time`)
VALUES (4, '三九胃泰颗粒', '胃胀、胃痛、胃不舒服', '清热祛湿，消炎止痛，理气除胀，养胃益肠。', '999', NULL,
        '1． 服药期间，忌食辛辣，油炸，过酸食物及酒类等刺激性食品。\n2． 十五天为一疗程，初显疗效后不宜立即停药，建议再服3—4个疗程以巩固疗效。\n3．胃寒患者慎用。',
        '开水冲服。一次1袋，一日2次。', 0,
        'https://kagg886.oss-cn-beijing.aliyuncs.com/%E4%B8%89%E4%B9%9D%E8%83%83%E6%B3%B0%E9%A2%97%E7%B2%92.jpeg', 15.00,
        '2024-03-02 12:58:32', '2024-03-26 12:51:49');
INSERT INTO `medicine` (`id`, `medicine_name`, `keyword`, `medicine_effect`, `medicine_brand`, `interaction`, `taboo`,
                        `us_age`, `medicine_type`, `img_path`, `medicine_price`, `create_time`, `update_time`)
VALUES (5, '999皮炎平', '皮肤瘙痒', '用于局限性瘙痒症、神经性皮炎、接触性皮炎、脂溢性皮炎以及慢性湿疹。', '999', NULL,
        '1.患处已破溃、化脓或有明显渗出者禁用。\n2.病毒感染者（如有疱疹、水痘）禁用。\n3.对本品成分过敏者禁用。',
        '皮肤外用。取少量涂于患处，并轻揉片刻；一日1~2次，病情较重或慢性炎症患者，每日5-8次或遵医嘱。', 0,
        'https://kagg886.oss-cn-beijing.aliyuncs.com/999%E7%9A%AE%E7%82%8E%E5%B9%B3.jpeg', 15.21,
        '2024-03-02 13:01:34', '2024-03-26 12:52:46');
INSERT INTO `medicine` (`id`, `medicine_name`, `keyword`, `medicine_effect`, `medicine_brand`, `interaction`, `taboo`,
                        `us_age`, `medicine_type`, `img_path`, `medicine_price`, `create_time`, `update_time`)
VALUES (6, '甲硝唑', '牙痛',
        '适应症为用于治疗肠道和肠外阿米巴病（如阿米巴肝脓肿、胸膜阿米巴病等）。还可用于治疗阴道滴虫病、小袋虫病和皮肤利什曼病、麦地那龙线虫感染等。目前还广泛用于厌氧菌感染的治疗',
        '奥可安', '本品能增强华法林等抗凝药物的作用。与土霉素合用可干扰甲硝唑清除阴道滴虫的作用。',
        '有活动性中枢神经系统疾患和血液病者禁用。', '成人一次两片，一日三次', 0,
        'https://kagg886.oss-cn-beijing.aliyuncs.com/%E7%94%B2%E7%A1%9D%E5%94%91.jpeg', 28.50,
        '2024-03-02 13:03:27', '2024-03-26 12:54:01');
INSERT INTO `medicine` (`id`, `medicine_name`, `keyword`, `medicine_effect`, `medicine_brand`, `interaction`, `taboo`,
                        `us_age`, `medicine_type`, `img_path`, `medicine_price`, `create_time`, `update_time`)
VALUES (7, '布洛芬缓释胶囊', '头疼、缓解痛',
        '用于缓解轻至中度疼痛如头痛、偏头痛、牙痛、痛经、关节痛、肌肉痛、神经痛，也用于普通感冒或流行性感冒引起的发热',
        '芬必得',
        '.本品与其他解热、镇痛、抗炎药物同用时可增加胃肠道不良反应，并可能导致溃疡。 2.本品与肝素、双香豆素类(如华法林)等抗凝药 同用时，可导致凝血酶原时间延长，增加出血倾向。 3.本品与地高辛、甲氨蝶呤、口服降血糖药物同用 时，能使这些药物的血药浓度增高，不宜同用。 ',
        '1.对其他非甾休抗炎药过敏者禁用。 2.孕妇及晡乳期妇女禁用。 3.对阿司匹林过敏的哮喘患者禁用。 4.严重肝肾功能不全者或严重心力衰竭者禁用。 5.正在服用其他含有布洛芬或其他非甾休抗炎药， 包括服用已知是特异性环氧化酶-2抑制剂药物的患者禁用。除非医生建议使用。 6.既往有与使用非甾体类抗炎药治疗相关的上消化道出血或穿孔史者禁用。 7.活动性或既往有消化性溃疡史，胃肠道出血或穿孔的患者禁用。',
        '口服。成人，一次1片，一日2次（早晚各一次）。', 1,
        'https://kagg886.oss-cn-beijing.aliyuncs.com/%E5%B8%83%E6%B4%9B%E8%8A%AC%E7%BC%93%E9%87%8A%E8%83%B6%E5%9B%8A.jpeg', 1.00,
        '2024-03-02 13:10:47', '2024-03-26 15:05:30');
COMMIT;

-- ----------------------------
-- Table structure for pageview
-- ----------------------------
DROP TABLE IF EXISTS `pageview`;
CREATE TABLE `pageview`
(
    `id`         int(1) NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `pageviews`  int(1)  DEFAULT NULL COMMENT '浏览量',
    `illness_id` int(11) DEFAULT NULL COMMENT '病的id',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 15
  DEFAULT CHARSET = utf8mb4;

-- ----------------------------
-- Records of pageview
-- ----------------------------
BEGIN;
INSERT INTO `pageview` (`id`, `pageviews`, `illness_id`)
VALUES (5, 5, 1);
INSERT INTO `pageview` (`id`, `pageviews`, `illness_id`)
VALUES (6, 4, 13);
INSERT INTO `pageview` (`id`, `pageviews`, `illness_id`)
VALUES (7, 2, 4);
INSERT INTO `pageview` (`id`, `pageviews`, `illness_id`)
VALUES (8, 1, 2);
INSERT INTO `pageview` (`id`, `pageviews`, `illness_id`)
VALUES (9, 1, 3);
INSERT INTO `pageview` (`id`, `pageviews`, `illness_id`)
VALUES (10, 1, 5);
INSERT INTO `pageview` (`id`, `pageviews`, `illness_id`)
VALUES (11, 1, 6);
INSERT INTO `pageview` (`id`, `pageviews`, `illness_id`)
VALUES (12, 2, 7);
INSERT INTO `pageview` (`id`, `pageviews`, `illness_id`)
VALUES (13, 1, 8);
INSERT INTO `pageview` (`id`, `pageviews`, `illness_id`)
VALUES (14, 1, 9);
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`           int(11) NOT NULL AUTO_INCREMENT COMMENT '用户主键id',
    `user_account` varchar(255) DEFAULT NULL COMMENT '用户账号',
    `user_name`    varchar(255) DEFAULT NULL COMMENT '用户的真实名字',
    `user_pwd`     varchar(255) DEFAULT NULL COMMENT '用户密码',
    `user_age`     int(11)      DEFAULT NULL COMMENT '用户年龄',
    `user_sex`     varchar(1)   DEFAULT NULL COMMENT '用户性别',
    `user_email`   varchar(255) DEFAULT NULL COMMENT '用户邮箱',
    `user_tel`     varchar(50)  DEFAULT NULL COMMENT '手机号',
    `role_status`  int(11)      DEFAULT NULL COMMENT '角色状态，1管理员，0普通用户',
    `img_path`     varchar(255) DEFAULT NULL COMMENT '用户头像',
    `create_time`  datetime     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`  datetime     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `create_time` (`create_time`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 6
  DEFAULT CHARSET = utf8mb4;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` (`id`, `user_account`, `user_name`, `user_pwd`, `user_age`, `user_sex`, `user_email`, `user_tel`,
                    `role_status`, `img_path`, `create_time`, `update_time`)
VALUES (4, 'admin', '管理员', '123456', 23, '男', '485184047@qq.com', '17746678954', 1,
        'https://q1.qlogo.cn/g?b=qq&nk=485184047&s=100', '2024-03-26 15:55:41',
        '2024-03-26 15:56:15');
INSERT INTO `user` (`id`, `user_account`, `user_name`, `user_pwd`, `user_age`, `user_sex`, `user_email`, `user_tel`,
                    `role_status`, `img_path`, `create_time`, `update_time`)
VALUES (5, 'liboyou', '杂鱼', '123456', 23, '女', '3396812897@qq.com', '17879544343', 0,
        'https://q1.qlogo.cn/g?b=qq&nk=3396812897&s=100', '2024-03-26 14:25:53',
        '2024-03-26 14:27:12');
COMMIT;


-- ----------------------------
-- Records of Video
-- ----------------------------
DROP TABLE IF EXISTS `video`;
CREATE TABLE `video`
(
    `id`          int(11) NOT NULL AUTO_INCREMENT,
    `title`       varchar(255) DEFAULT NULL,
    `description` text,
    `link`        varchar(255) DEFAULT 'www.baidu.com',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4;

BEGIN;
INSERT INTO `video` (`title`, `description`, `link`)
VALUES ('医药政策改革：惠及亿万患者的新篇章', '本视频深入解析了最新医药政策改革的内容，探讨了这些政策如何降低药品价格、提高医疗质量，并最终惠及亿万患者。通过专家解读和案例分析，观众可以全面了解政策背后的意义和深远影响。', 'https://yinlin712.oss-cn-beijing.aliyuncs.com/4/c120996a8f9ad539a2c8aa79b8488900.mp4'),
       ('医保政策解读：让每一分钱都花在刀刃上', '本视频针对医保政策进行了全面解读，包括医保覆盖范围、报销比例、报销流程等关键信息。通过深入浅出的讲解和实例分析，帮助观众理解医保政策的重要性，并学会如何充分利用医保资源。', 'https://yinlin712.oss-cn-beijing.aliyuncs.com/4/c120996a8f9ad539a2c8aa79b8488900.mp4'),
       ('医药监管新政：保障药品安全，守护百姓健康', '本视频介绍了医药监管新政的实施背景、目的和内容，以及这些政策如何加强药品监管、保障药品质量和安全。通过权威专家的解读和现场采访，让观众深刻认识到药品安全的重要性。', 'https://yinlin712.oss-cn-beijing.aliyuncs.com/4/c120996a8f9ad539a2c8aa79b8488900.mp4'),
       ('药品带量采购政策：降低药价，缓解看病贵问题', '本视频详细解读了药品带量采购政策的实施情况，包括政策背景、目标、实施过程及成效。通过数据分析和案例分享，展示了这一政策如何有效降低药品价格，缓解看病贵问题，让更多人享受到优质医疗服务。', 'https://yinlin712.oss-cn-beijing.aliyuncs.com/4/c120996a8f9ad539a2c8aa79b8488900.mp4'),
       ('医药创新政策：激发行业活力，推动科技进步', '本视频聚焦医药创新政策，介绍了政策如何鼓励医药企业加大研发投入、推动新药研发进程。通过展示医药创新成果和企业家的心声，激发观众对医药创新的关注和支持，共同推动医药行业科技进步。', 'https://yinlin712.oss-cn-beijing.aliyuncs.com/4/c120996a8f9ad539a2c8aa79b8488900.mp4'),
       ('中医药发展政策：传承国粹，助力健康中国', '本视频深入探讨了中医药发展政策的内容和意义，包括政策如何支持中医药传承与创新、推动中医药国际化等方面。通过展示中医药的独特魅力和现代医学价值，激发观众对中医药的认同感和自豪感，共同助力健康中国建设。', 'https://yinlin712.oss-cn-beijing.aliyuncs.com/4/c120996a8f9ad539a2c8aa79b8488900.mp4');
COMMIT;


-- ----------------------------
-- Records of Channel
-- ----------------------------
DROP TABLE IF EXISTS `Channel`;
CREATE TABLE `Channel`
(
    `id`   int(11) NOT NULL AUTO_INCREMENT,
    `type` int(11)      DEFAULT NULL,
    `name` text,
    `icon` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4;

BEGIN;
INSERT INTO `Channel` (`type`, `name`, `icon`)
VALUES (0, '感冒', 'https://randomuser.me/api/portraits/women/85.jpg'),
       (0, '脚气', 'https://randomuser.me/api/portraits/women/85.jpg'),
       (1, '梅毒', 'https://randomuser.me/api/portraits/women/85.jpg'),
       (1, '艾滋病', 'https://randomuser.me/api/portraits/women/85.jpg');
COMMIT;


-- ----------------------------
-- Records of Channel History
-- ----------------------------
DROP TABLE IF EXISTS `ChannelHistory`;
CREATE TABLE `ChannelHistory`
(
    `id`          int(11) NOT NULL AUTO_INCREMENT,
    `channel_id`  int(11) NOT NULL,
    `user_id`     int(11) NOT NULL,
    `message`     text     DEFAULT NULL,
    `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4;

BEGIN;
INSERT INTO `ChannelHistory` (`channel_id`, `user_id`, `message`)
VALUES (1, 4, '感冒'),
       (1, 5, '脚气'),
       (1, 4, '乳腺癌'),
       (2, 4, '梅毒'),
       (2, 4, '糖尿病');
COMMIT;


-- ----------------------------
-- Records of PostList
-- ----------------------------
DROP TABLE IF EXISTS `PostList`;
CREATE TABLE `PostList`
(
    `id`          int(11) NOT NULL AUTO_INCREMENT,
    `user_id`     int(11) NOT NULL,
    `channel_id`  int(11) NOT NULL,
    `title`       text     DEFAULT NULL,
    `description`     text     DEFAULT NULL,
    `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
    `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4;

BEGIN;
INSERT INTO `PostList` (`user_id`, `channel_id`, `title`, `description`)
VALUES (4, 3, '测试标题1', '测试1'),
       (5, 3, '测试标题2', '测试2'),
       (5, 4, '测试标题3', '测试3');
COMMIT;

-- ----------------------------
-- Records of PostReply
-- ----------------------------
DROP TABLE IF EXISTS `PostReply`;
CREATE TABLE `PostReply`
(
    `id`          int(11) NOT NULL AUTO_INCREMENT,
    `user_id`     int(11) NOT NULL,
    `post_id`  int(11) NOT NULL,
    `message`     text     DEFAULT NULL,
    `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4;

BEGIN;
INSERT INTO `PostReply` (`user_id`, `post_id`, `message`)
VALUES (4, 1, '这里是1l'),
       (5, 1, '这里是2l'),
       (5, 2, '这里是3l');
COMMIT;




SET FOREIGN_KEY_CHECKS = 1;


DROP TABLE IF EXISTS article;
DROP TABLE IF EXISTS category;
-- 分类表
create table category(
                         id int unsigned primary key auto_increment comment 'ID',
                         category_name varchar(32) not null comment '分类名称',
                         create_user int(11) not null comment '创建人ID',
                         create_time datetime DEFAULT CURRENT_TIMESTAMP,
												 update_time datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                         constraint fk_category_user foreign key (create_user) references user(id) -- 外键约束
);


-- 文章表
create table article(
                        id int unsigned primary key auto_increment comment 'ID',
                        title varchar(30) not null comment '文章标题',
                        content varchar(10000) not null comment '文章内容',
                        category_id int unsigned comment '文章分类ID',
                        create_user int(11) not null comment '创建人ID',
                        create_time datetime DEFAULT CURRENT_TIMESTAMP,
												update_time datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                        constraint fk_article_category foreign key (category_id) references category(id),-- 外键约束
                        constraint fk_article_user foreign key (create_user) references user(id) -- 外键约束
);

INSERT INTO article (title, content, category_id, create_user) VALUES('2', '2222', 1, 4);
INSERT INTO article (title, content, category_id, create_user) VALUES('2', '2222', 1, 4);
INSERT INTO article (title, content, category_id, create_user) VALUES('2', '2222', 1, 4);
INSERT INTO article (title, content, category_id, create_user) VALUES('2', '2222', 1, 4);
INSERT INTO article (title, content, category_id, create_user) VALUES('2', '2222', 1, 4);
INSERT INTO article (title, content, category_id, create_user) VALUES('2', '2222', 1, 4);
INSERT INTO article (title, content, category_id, create_user) VALUES('2', '2222', 1, 4);
INSERT INTO article (title, content, category_id, create_user) VALUES('2', '2222', 1, 4);
INSERT INTO article (title, content, category_id, create_user) VALUES('2', '2222', 1, 4);
INSERT INTO article (title, content, category_id, create_user) VALUES('2', '2222', 1, 4);
INSERT INTO article (title, content, category_id, create_user) VALUES('2', '2222', 1, 4);
INSERT INTO article (title, content, category_id, create_user) VALUES('2', '2222', 1, 4);
INSERT INTO article (title, content, category_id, create_user) VALUES('2', '2222', 1, 4);
INSERT INTO article (title, content, category_id, create_user) VALUES('2', '2222', 1, 4);
INSERT INTO article (title, content, category_id, create_user) VALUES('2', '2222', 1, 4);
INSERT INTO article (title, content, category_id, create_user) VALUES('2', '2222', 1, 4);

