package jpabook.jpashop.service;

import jpabook.jpashop.dto.AttachDto;
import lombok.Getter;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@Getter
public class FileService {
	@Value("${file.upload.path}")
	private String uploadPath;

	/** 다중 MultipartFile에서 VO 설정 및 업로드 파일 처리 후 List 리턴 */
	public List<AttachDto> getAttachListByMultiparts(MultipartFile[] boFiles, String category, String path)
			throws IOException {
		List<AttachDto> atchList = new ArrayList<AttachDto>();
		for (int i = 0; i < boFiles.length; i++) {
			MultipartFile multipart = boFiles[i];
			AttachDto attachDto = this.getAttachByMultipart(multipart, category, path);
			if (attachDto != null) {
				atchList.add(attachDto);
			}
		}
		return atchList;
	}

	/** MultipartFile에서 VO 설정 및 업로드 파일 처리 후 리턴, 없는 경우 null */
	public AttachDto getAttachByMultipart(MultipartFile multipart, String category, String path) throws IOException {
		if (!multipart.isEmpty()) {
			String fileName = UUID.randomUUID().toString();
			AttachDto attachDto = new AttachDto();

			attachDto.setAttachOriginalName(multipart.getOriginalFilename());
			attachDto.setAttachFileSize(multipart.getSize());
			attachDto.setAttachContentType(multipart.getContentType());
			attachDto.setAttachFileName(fileName);
			attachDto.setAttachCategory(category);
			attachDto.setAttachPath(path);
			attachDto.setAttachFancySize(fancySize(multipart.getSize()));
			String filePath = uploadPath + File.separatorChar + attachDto.getAttachPath();

			FileUtils.copyInputStreamToFile(multipart.getInputStream(), new File(filePath, fileName));
			// 여기서 실제 파일이 저장(regist에서 실행됬다), inputStream을 file로 변환하는 메소드
			// multipart.transferTo(new File(filePath, fileName)); // 비슷한 역할
			return attachDto;
		} else {
			return null;
		}
	}


	//이거 왜 만들었지?
	public String findFile(AttachDto attachDto){
		String filePath = uploadPath + File.separatorChar + attachDto.getAttachPath();
		return filePath+attachDto.getAttachFileName();  //저거 사이에 File.separatorChar 필요한거 같은데.
	}

	public String findFileByPathAndName(String filePath,String fileName){
		return uploadPath+File.separatorChar+filePath+File.separatorChar+fileName;
	}



	private DecimalFormat df = new DecimalFormat("#,###.0");

	private String fancySize(long size) {
		if (size < 1024) { // 1k 미만
			return size + " Bytes";
		} else if (size < (1024 * 1024)) { // 1M 미만
			return df.format(size / 1024.0) + " KB";
		} else if (size < (1024 * 1024 * 1024)) { // 1G 미만
			return df.format(size / (1024.0 * 1024.0)) + " MB";
		} else {
			return df.format(size / (1024.0 * 1024.0 * 1024.0)) + " GB";
		}
	}

}