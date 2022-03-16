package kopo.poly.service;

import kopo.poly.dto.NoticeDTO;
<<<<<<< HEAD
<<<<<<< HEAD

import java.awt.print.Pageable;
import java.util.List;
import java.util.Map;
=======
import java.util.List;
>>>>>>> aca8c8a62c55bf03fab80311b9e0d446ec850867
=======
import java.util.List;
>>>>>>> aca8c8a62c55bf03fab80311b9e0d446ec850867


public interface INoticeService {

	List<NoticeDTO> getNoticeList() throws Exception;
	
	void InsertNoticeInfo(NoticeDTO pDTO) throws Exception;

	NoticeDTO getNoticeInfo(NoticeDTO pDTO) throws Exception;

	void updateNoticeInfo(NoticeDTO pDTO) throws Exception;
	
	void deleteNoticeInfo(NoticeDTO pDTO) throws Exception;
<<<<<<< HEAD
<<<<<<< HEAD

=======
	
>>>>>>> aca8c8a62c55bf03fab80311b9e0d446ec850867
=======
	
>>>>>>> aca8c8a62c55bf03fab80311b9e0d446ec850867
}

